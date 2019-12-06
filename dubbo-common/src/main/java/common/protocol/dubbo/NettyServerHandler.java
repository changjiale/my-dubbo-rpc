package common.protocol.dubbo;

import common.registry.LocalRegister;
import common.request.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * @author: changjiale
 * @create: 2019/12/06 14:41
 * @description:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        System.out.println("channelActive:" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest rpcRequest = (RpcRequest)msg;
        Class implClass= LocalRegister.get(rpcRequest.getClassName());
        Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
        String result = (String) method.invoke(implClass.newInstance(), rpcRequest.getParams());
        System.out.println("Netty===============" + result);
        ctx.writeAndFlush(result);
        ctx.flush();
        ctx.close();
    }

}
