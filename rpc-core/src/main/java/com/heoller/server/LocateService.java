package com.heoller.server;

import com.heoller.entity.Request;

import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉
 *
 * @author 19093070
 * @date 2021/1/9 15:56
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface LocateService {

    Object findOrCreateObject(String clazz);

    Method findMethodByArg(Object obj, Request request);
}
