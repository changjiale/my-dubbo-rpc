package common.protocol;

import common.Protocol;
import common.URL;
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
        //new NettyServer()
    }

    @Override
    public String send(URL url, RpcRequest rpcRequest) {
        return null;
    }
}
