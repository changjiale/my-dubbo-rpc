package common.protocol.http;

import common.URL;
import common.registry.LocalRegister;
import common.request.RpcRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import org.apache.commons.io.IOUtils;

/**
 * @author: changjiale
 * @create: 2019/12/05 11:09
 * @description:
 */
public class HttpServerHandler {
    public void handle(HttpServletRequest req, HttpServletResponse resp){
       try {
           //获取输入流
           ServletInputStream inputStream = req.getInputStream();
           //包装成对象输入流
           ObjectInputStream ois=new ObjectInputStream(inputStream);
           //转换成方法调用参数
           RpcRequest rpcRequest = (RpcRequest) ois.readObject();
           Class implClass= LocalRegister.get(rpcRequest.getClassName());
           Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
           String result = (String) method.invoke(implClass.newInstance(), rpcRequest.getParams());
           //写回结果
           IOUtils.write(result,resp.getOutputStream());
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (NoSuchMethodException e) {
           e.printStackTrace();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       } catch (InstantiationException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       }

   }
}
