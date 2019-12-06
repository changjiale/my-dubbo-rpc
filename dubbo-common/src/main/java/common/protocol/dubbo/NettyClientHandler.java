package common.protocol.dubbo;

import common.request.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;

import java.util.concurrent.Callable;

/**
 * @author: changjiale
 * @create: 2019/12/06 14:42
 * @description:
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private RpcRequest rpcRequest;
    private Future future;

    public RpcRequest getInvocation() {
        return rpcRequest;
    }

    public void setRpcRequest(RpcRequest rpcRequest) {
        this.rpcRequest = rpcRequest;
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        future = ctx.writeAndFlush(rpcRequest);
    }

    @Override
    public Object call() throws Exception {
        return future;
    }
}
