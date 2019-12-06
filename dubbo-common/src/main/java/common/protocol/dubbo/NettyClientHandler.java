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
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private Object response;

    public Object getResponse() {
        return response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ctx 向服务端写数据   msg接收服务端消息

        //将服务端返回的内容返回
        response = msg;
    }
}
