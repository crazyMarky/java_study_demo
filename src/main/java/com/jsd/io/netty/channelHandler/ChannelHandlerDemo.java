package com.jsd.io.netty.channelHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChannelHandlerDemo {

    private static int port = 9999;

    public  void connet() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        //在ChannelInitializer()中可以设置pipeline()中的顺序，里面有丰富的api来设置管道的流向
        serverBootstrap.group(group).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                //直接运行客户端和测试端，可以快速得出整个生命周期
                //总结一:整个出站入站的顺序是入站：从左到右，出站从右到左，同一个管道中的Handler需要向下传递触发
                //有意思的是出站的方法是在多个时候会触发
                channel.pipeline().addLast(new ChannelInboundHandlerLifeCycleDemo()).addFirst(new ChannelOutboundHandlerLifeCycleDemo());
            }
        });
        try {
            ChannelFuture sync = serverBootstrap.bind(port).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        ChannelHandlerDemo nettyServerByteBuffDemo = new ChannelHandlerDemo();
        nettyServerByteBuffDemo.connet();
    }
}
