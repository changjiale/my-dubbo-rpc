package common.proxy.server;

import common.request.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: changjiale
 * @create: 2019/11/25 19:07
 * @description:
 */
public class RpcServerHandler extends ChannelInboundHandlerAdapter {

    private Map<String, Object> handlerMap = new HashMap<>();

    public RpcServerHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        System.out.println("channelActive:" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ctx 写东西给客户端， msg客户端发送过来的消息
        System.out.println("服务端接收到消息：" + msg);
        RpcRequest rpcRequest = (RpcRequest) msg;
        Object result = new Object();
        if (handlerMap.containsKey(rpcRequest.getClassName())) {
            Object clazz = handlerMap.get(rpcRequest.getClassName());
            Method method = clazz.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
            result = method.invoke(clazz, rpcRequest.getParams());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }
}
