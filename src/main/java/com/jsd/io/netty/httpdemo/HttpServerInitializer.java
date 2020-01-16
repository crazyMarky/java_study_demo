package com.jsd.io.netty.httpdemo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        // http 编解码
        channel.pipeline().addLast(new HttpServerCodec());
        // http 消息聚合器
        channel.pipeline().addLast("httpAggregator",new HttpObjectAggregator(512*1024));
        channel.pipeline().addLast(new HttpRequestChannelDemo());
    }
}
