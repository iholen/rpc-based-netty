package com.heoller.client;

import com.alibaba.fastjson.JSON;
import com.heoller.entity.Request;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.util.concurrent.CountDownLatch;

/**
 * 〈一句话功能简述〉
 *
 * @author 19093070
 * @date 2021/1/9 16:34
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ServiceLocator extends NettyClientInboundHandler implements ExecuteService {

    private String clazz;

    public ServiceLocator() {
    }

    public ServiceLocator(String clazz) {
        this.clazz = clazz;
    }

    /**
     * rpc调用
     *
     * @param method
     * @param requestStr
     * @return
     */
    public String execute(String method, String requestStr) {
        countDownLatch = new CountDownLatch(1);
        Request request = new Request(clazz, method, requestStr);
        doSend(request);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            // TODO: add logger
        }
        String result = sb.toString();
        sb.setLength(0);
        return result;
    }

    @Override
    public void doSend(Request request) {
        ByteBuf buf = Unpooled.copiedBuffer(JSON.toJSONString(request), CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
    }
}
