package com.jsd.io.netty.bytebuff;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class ByteBufAllocatorDemo extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        Charset utf8 = Charset.forName("UTF-8");
        System.out.println(byteBuf.toString(utf8));
    }
}
