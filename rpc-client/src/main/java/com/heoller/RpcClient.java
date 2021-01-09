package com.heoller;

import com.heoller.client.Client;
import com.heoller.client.ServiceLocator;

/**
 * 〈一句话功能简述〉
 *
 * @author 19093070
 * @date 2021/1/9 17:06
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RpcClient {

    public static void main(String[] args) {
        ServiceLocator serviceLocator = new ServiceLocator("com.heoller.MyService");
        Thread thread = new Thread(() -> new Client(serviceLocator));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(serviceLocator.execute("helloRPC", null));
        System.out.println(serviceLocator.execute("helloRPC", "九阴真经"));
    }
}
