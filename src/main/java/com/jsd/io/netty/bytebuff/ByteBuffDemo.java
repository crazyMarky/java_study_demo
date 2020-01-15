package com.jsd.io.netty.bytebuff;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.Random;

public class ByteBuffDemo extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        Charset utf8 = Charset.forName("UTF-8");

        //使用一个copy可以深拷贝一个ByteBuf
        ByteBuf copy = byteBuf.copy();
        //可以使用silce()来划分一部分的区域复制(浅拷贝)
        ByteBuf slice = byteBuf.slice();

        System.out.println(copy.toString(utf8));
        System.out.println(slice.toString(utf8));
        Random random = new Random();
        /**
         * ByteBuf是netty基于JDK的ByteBuf重新封装的一套api，用于处理网络io中的字节传输，比JDK上面的性能要好，占用内存更少
         * ByteBuf 使用zero-based 的 indexing(从0开始的索引)，第一个字节的索引是 0，最后一个字节的索引是 ByteBuf 的 capacity - 1，下面代码是遍历 ByteBuf 的所有字节：
         */
        for (int i = 0; i < byteBuf.capacity(); i++) {
            byte aByte = byteBuf.getByte(i);
            System.out.print(aByte);
        }
        /**
         * ByteBuf内部维护两个索引:readerIndex （读索引）和 writerIndex（写索引），在get和set的时候并不会推动索引
         * ByteBuf 提供两个指针变量支付读和写操作，读操作是使用 readerIndex()，写操作时使用 writerIndex()。
         * 这和JDK的ByteBuffer不同，ByteBuffer只有一个方法来设置索引，所以需要使用 flip() 方法来切换读和写模式。
         * ByteBuf 一定符合：0 <= readerIndex <= writerIndex <= capacity。
         * 当readerIndex增加的时候，小于readerIndex的区域被称为可丢弃的区域
         * 他们可以被丢弃，通过调用discardReadBytes() 来回收空间。这个段的初始大小存储在readerIndex，为 0，当“read”操作被执行时递增（“get”操作不会移动 readerIndex）。
         * ByteBuf.discardReadBytes() 可以用来清空 ByteBuf 中已读取的数据，
         * 从而使 ByteBuf 有多余的空间容纳新的数据，但是discardReadBytes() 可能会涉及内存复制，
         * 因为它需要移动 ByteBuf 中可读的字节到开始位置，这样的操作会影响性能，一般在需要马上释放内存的时候使用收益会比较大。
         * 以下展示一个读取ByteBuf的操作
         */
        while (byteBuf.isReadable()) {
            System.out.println(byteBuf.readByte());
        }
        //循环读取之后，将可丢弃区域清除,用于写入准备,丢弃
        byteBuf.discardReadBytes();
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        //写入数据,也可以用set来使用，
        while (byteBuf.writableBytes() >= 4) {
            byteBuf.writeInt(random.nextInt());
        }
        //可以设置和重新定位ByteBuf readerIndex 和 writerIndex 通过调用 markReaderIndex(), markWriterIndex(), resetReaderIndex() 和 resetWriterIndex()
        //可以通过调用 readerIndex(int) 或 writerIndex(int) 将指标移动到指定的位置。在尝试任何无效位置上设置一个索引将导致 IndexOutOfBoundsException 异常。
        //调用 clear() 可以同时设置 readerIndex 和 writerIndex 为 0。注意，这不会清除内存中的内容
        //clear()之后 ByteBuf 空间都是可写的了。
        //clear() 比 discardReadBytes() 更低成本，因为他只是重置了索引，而没有内存拷贝。
        byteBuf.clear();
        System.out.println(byteBuf.toString(utf8));
        System.out.println(slice.toString(utf8));
        //为了减少分配和释放内存的开销，Netty 通过支持池类 ByteBufAllocator，可用于分配的任何 ByteBuf 我们已经描述过的类型的实例
        ByteBufAllocator alloc = channelHandlerContext.alloc();
        ByteBuf buffer = alloc.buffer();
        String a = "channel1 give it to u";
        buffer.writeBytes(a.getBytes(utf8));
        //Unpooled （非池化）缓存
        //当未引用 ByteBufAllocator 时，上面的方法无法访问到 ByteBuf。
        // 对于这个用例 Netty 提供一个实用工具类称为 Unpooled,，它提供了静态辅助方法来创建非池化的 ByteBuf 实例
        ByteBuf buf = Unpooled.copiedBuffer("channel1 give it to u", utf8);
//        System.out.println(buf.toString());
        channelHandlerContext.write(buffer);
    }
}
