package com.jsd.io.netty.bytebuff;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServerByteBuffDemo {

    private static int port = 9999;

    public  void connet() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        serverBootstrap.group(group).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new ByteBuffDemo()).addLast(new ByteBufAllocatorDemo());
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
        NettyServerByteBuffDemo nettyServerByteBuffDemo = new NettyServerByteBuffDemo();
        nettyServerByteBuffDemo.connet();
    }
}
