package com.jsd.io.netty.channelHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ChannelInboundHandlerLifeCycleDemo extends ChannelInboundHandlerAdapter {
    /**
     * 当Channel注册的时候就会发生的事件
     * Invoked when a Channel is registered to its EventLoop and is able to handle I/O.
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1" + " channelRegistered");
        super.channelRegistered(channelHandlerContext);
    }

    /**
     * 当被取消Channel注册的时候就会发生的事件
     * Invoked when a Channel is deregistered from its EventLoop and cannot handle any I/O.
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1" + " channelUnregistered");
        super.channelUnregistered(channelHandlerContext);
    }

    /**
     * 当当前的Channel是处于活动的，通常为连接发生的时候
     * Invoked when a Channel is active; the Channel is connected/bound and ready.
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1" + " channelActive");
        super.channelActive(channelHandlerContext);
    }

    /**
     * 当当前的Channel不在保持活动，通常为连接断开的时候
     * Invoked when a Channel leaves active state and is no longer connected to its remote peer.
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1" + " channelInactive");
        super.channelInactive(channelHandlerContext);
    }

    /**
     * 当连接后传输了数据就会触发的事件
     * Invoked if data are read from the Channel.
     *
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("channel1" + " channelRead");
        ByteBuf buffer = channelHandlerContext.alloc().buffer();
        buffer.writeBytes("channel1 writedata".getBytes());
        channelHandlerContext.writeAndFlush(buffer);
        super.channelRead(channelHandlerContext,o);
    }

    /**
     * 当读取数据结束之后会触发的事件
     * Invoked when a read operation on the Channel has completed.
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1" + " channelReadComplete");
        super.channelReadComplete(channelHandlerContext);
    }

    /**
     * Invoked when a user calls Channel.fireUserEventTriggered(...) to pass a pojo through the ChannelPipeline.
     * This can be used to pass user specific events through the ChannelPipeline and so allow handling those events.
     *
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("channel1" + " userEventTriggered");
        super.userEventTriggered(channelHandlerContext,o);
    }

    /**
     * Invoked when the writability state of the Channel changes.
     * The user can ensure writes are not done too fast (with risk of an OutOfMemoryError) or can resume writes
     * when the Channel becomes writable again.Channel.isWritable() can be used to detect the actual writability of the channel.
     * The threshold for writability can be set via Channel.config().setWriteHighWaterMark() and Channel.config().setWriteLowWaterMark().
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1" + " channelWritabilityChanged");
        super.channelWritabilityChanged(channelHandlerContext);
    }

    /**
     * 当 ChannelHandler 添加到 ChannelPipeline 调用
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1" + " handlerAdded");
        super.handlerAdded(channelHandlerContext);
    }

    /**
     * 当 ChannelHandler 从 ChannelPipeline 移除时调用
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1" + " handlerRemoved");
        super.handlerRemoved(channelHandlerContext);
    }

    /**
     * 当 ChannelPipeline 执行抛出异常时调用
     *
     * @param channelHandlerContext
     * @param throwable
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        System.out.println("channel1" + " exceptionCaught");
    }
}
