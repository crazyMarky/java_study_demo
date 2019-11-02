package com.jsd.nio.nioChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/** 聊天的客户端，可以创建多个实例实现多客户端
 *  NIO客户端
 */
public class NioClient {

    /**
     * 启动
     * @param nickname 昵称
     */
    public void start(String nickname) throws IOException {
        /**
         * 连接服务器端
         */
        SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("120.78.193.119", 8000));

        /**
         * 接收服务器端响应
         */
        // 新开线程，专门负责来接收服务器端的响应数据
        // selector ， socketChannel ， 注册
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();

        /**
         * 向服务器端发送数据
         */
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String request = scanner.nextLine();
            if (request != null && request.length() > 0) {
                socketChannel.write(
                        Charset.forName("UTF-8")
                                .encode(nickname + " : " + request));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new NioClient().start("昵称");
    }

}
