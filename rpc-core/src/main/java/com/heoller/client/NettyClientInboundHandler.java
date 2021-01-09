package com.heoller.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.CountDownLatch;

/**
 * 〈一句话功能简述〉
 *
 * @author 19093070
 * @date 2021/1/9 15:25
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class NettyClientInboundHandler extends ChannelInboundHandlerAdapter {

    /**
     * 接收数据
     */
    StringBuffer sb = new StringBuffer();
    ChannelHandlerContext ctx;
    CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与服务端已建立链接");
        this.ctx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        sb.append(buf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        countDownLatch.countDown();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
