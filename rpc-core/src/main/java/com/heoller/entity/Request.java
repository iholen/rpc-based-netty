package com.heoller.entity;

/**
 * 〈一句话功能简述〉
 *
 * @author 19093070
 * @date 2021/1/9 15:59
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Request {

    private String clazz;
    private String method;
    private String arg;

    public Request() {

    }

    public Request(String clazz, String method, String arg) {
        this.clazz = clazz;
        this.method = method;
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Request{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", arg='" + arg + '\'' +
                '}';
    }
}
