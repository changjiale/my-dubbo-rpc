package common.protocol;

import common.Protocol;
import common.URL;
import common.protocol.dubbo.NettyClient;
import common.protocol.dubbo.NettyServer;
import common.request.RpcRequest;

/**
 * @author: changjiale
 * @create: 2019/12/06 14:50
 * @description:
 */
public class DubboProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new NettyServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Object send(URL url, RpcRequest rpcRequest) {
        return new NettyClient().send(url.getHostname(), url.getPort(), rpcRequest);
    }
}
