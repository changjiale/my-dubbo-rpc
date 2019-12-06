package common.protocol.dubbo;

import common.registry.LocalRegister;
import common.request.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.lang.reflect.Method;

/**
 * @author: changjiale
 * @create: 2019/12/06 14:41
 * @description:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest rpcRequest = (RpcRequest)msg;
        Class implClass = LocalRegister.get(rpcRequest.getClassName());
        Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
        String invoke = (String) method.invoke(implClass, rpcRequest.getParams());
        System.out.println("Netty===============" + invoke);
        ctx.writeAndFlush("Netty: " + invoke);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
