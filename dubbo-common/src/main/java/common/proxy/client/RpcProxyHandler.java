package common.proxy.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: changjiale
 * @create: 2019/11/25 17:47
 * @description:
 */
public class RpcProxyHandler extends ChannelInboundHandlerAdapter {
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