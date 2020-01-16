package com.jsd.io.netty.httpdemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HttpServerDemo {

    private int port = 9999;

    public void startNetty() {
        NioEventLoopGroup acceptor = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(acceptor, worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new HttpServerInitializer());
            ChannelFuture sync = bootstrap.bind(port).sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            acceptor.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        HttpServerDemo httpServerDemo = new HttpServerDemo();
        httpServerDemo.startNetty();
    }
}
