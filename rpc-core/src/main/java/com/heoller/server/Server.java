package com.heoller.server;

import com.heoller.server.FinalServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 〈一句话功能简述〉
 *
 * @author 19093070
 * @date 2021/1/9 15:08
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Server {

    public Server() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new FinalServerHandler());
            }
        });

        try {
            serverBootstrap.bind(9000).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
