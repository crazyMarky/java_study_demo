package com.jsd.io.netty.channelHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;
import java.nio.charset.Charset;

public class ChannelOutboundHandlerLifeCycleDemo extends ChannelOutboundHandlerAdapter {

    /**
     * Invoked on request to bind the Channel to a local address
     *
     * @param channelHandlerContext
     * @param socketAddress
     * @param channelPromise
     * @throws Exception
     */
    @Override
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        System.out.println("channel1out" + " bind");
        super.bind(channelHandlerContext, socketAddress, channelPromise);
    }

    /**
     * Invoked on request to connect the Channel to the remote peer
     *
     * @param channelHandlerContext
     * @param socketAddress
     * @param socketAddress1
     * @param channelPromise
     * @throws Exception
     */
    @Override
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress1, ChannelPromise channelPromise) throws Exception {
        System.out.println("channel1out" + " connect");
        super.connect(channelHandlerContext, socketAddress, socketAddress1, channelPromise);
    }

    /**
     * Invoked on request to disconnect the Channel from the remote peer
     *
     * @param channelHandlerContext
     * @param channelPromise
     * @throws Exception
     */
    @Override
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        System.out.println("channel1out" + " disconnect");
        super.disconnect(channelHandlerContext, channelPromise);
    }

    /**
     * Invoked on request to close the Channel
     *
     * @param channelHandlerContext
     * @param channelPromise
     * @throws Exception
     */
    @Override
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        System.out.println("channel1out" + " close");
        super.close(channelHandlerContext, channelPromise);
    }

    /**
     * Invoked on request to deregister the Channel from its EventLoop
     *
     * @param channelHandlerContext
     * @param channelPromise
     * @throws Exception
     */
    @Override
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        System.out.println("channel1out" + " deregister");
        super.deregister(channelHandlerContext, channelPromise);
    }

    /**
     * Invoked on request to read more data from the Channel
     * 出站的read方法会优先于入站的read方法(原因未知)
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1out" + " read");
        super.read(channelHandlerContext);
    }

    /**
     * Invoked on request to write data through the Channel to the remote peer
     *
     * @param channelHandlerContext
     * @param o
     * @param channelPromise
     * @throws Exception
     */
    @Override
    public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {
        ByteBuf msg = (ByteBuf) o;
        System.out.println(msg.toString(Charset.forName("UTF-8")));
        System.out.println("channel1out" + " write");
        super.write(channelHandlerContext, o, channelPromise);
    }

    /**
     * Invoked on request to flush queued data to the remote peer through the Channel
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1out" + " flush");
        super.flush(channelHandlerContext);
    }

    /**
     * 当 ChannelHandler 添加到 ChannelPipeline 调用
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channel1out" + " handlerAdded");
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
        System.out.println("channel1out" + " handlerRemoved");
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
        System.out.println("channelout1" + " exceptionCaught");
    }
}
