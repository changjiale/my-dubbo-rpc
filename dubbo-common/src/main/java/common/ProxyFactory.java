package common;

import common.protocol.http.HttpClient;
import common.registry.RemoteMapRegister;
import common.request.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @author: changjiale
 * @create: 2019/12/05 11:37
 * @description:
 */
public class ProxyFactory {
    public static <T> T getProxy(Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient httpClient = new HttpClient();
                RpcRequest rpcRequest = new RpcRequest(interfaceClass.getName(), method.getName(),method.getParameterTypes(), args);
                URL url = RemoteMapRegister.random(interfaceClass.getName());
                String result = httpClient.send(url.getHostname(), url.getPort(), rpcRequest);

                return result;
            }
        });

    }
}
