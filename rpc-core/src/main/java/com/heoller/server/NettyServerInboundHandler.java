package com.heoller.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 〈一句话功能简述〉
 *
 * @author 19093070
 * @date 2021/1/9 15:25
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class NettyServerInboundHandler extends ChannelInboundHandlerAdapter implements ExecuteService {

    /**
     * 接收数据
     */
    StringBuffer sb = new StringBuffer();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        sb.append(buf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 处理业务代码
        String responseData = doBusiness();
        ByteBuf buf = Unpooled.copiedBuffer(responseData, CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
        sb.setLength(0);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
