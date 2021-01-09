package com.heoller.server;

import com.alibaba.fastjson.JSON;
import com.heoller.entity.Request;

import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉
 *
 * @author 19093070
 * @date 2021/1/9 16:03
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class FinalServerHandler extends NettyServerInboundHandler implements ParseRequestService, LocateService {

    @Override
    public String doBusiness() {
        Request request = doParse();
        Object object = findOrCreateObject(request.getClazz());
        Method method = findMethodByArg(object, request);
        Object result = "";
        try {
            if (request.getArg() == null) {
                result = method.invoke(object);
            } else {
                result = method.invoke(object, request.getArg());
            }
        } catch (Exception e) {
            // TODO: add logger
        }
        if (result instanceof String) {
            return (String)result;
        }
        return JSON.toJSONString(result);
    }

    @Override
    public Request doParse() {
        return JSON.parseObject(sb.toString(), Request.class);
    }

    /**
     * 可添加对象缓存
     *
     * @param clazz
     * @return
     */
    @Override
    public Object findOrCreateObject(String clazz) {
        try {
            Class<?> aClass = Class.forName(clazz);
            return aClass.newInstance();
        } catch (Exception e) {
            // TODO: add logger
        }
        return null;
    }

    @Override
    public Method findMethodByArg(Object obj, Request request) {
        Method method = null;
        if (obj == null) {
            return null;
        }

        try {
            if (null == request.getArg()) {
                method = obj.getClass().getDeclaredMethod(request.getMethod());
            } else {
                method = obj.getClass().getDeclaredMethod(request.getMethod(), String.class);
            }
        } catch (NoSuchMethodException e) {
            // TODO: add logger
        }
        return method;
    }
}
