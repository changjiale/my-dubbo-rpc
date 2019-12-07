package common.protocol;

import common.Protocol;
import common.URL;
import common.protocol.dubbo.NettyClient;
import common.protocol.dubbo.NettyServer;
import common.protocol.http.HttpClient;
import common.protocol.http.HttpServer;
import common.request.RpcRequest;

/**
 * @author: changjiale
 * @create: 2019/12/06 14:50
 * @description:
 */
public class HttpProtocol implements Protocol {
    @Override
    public void start(URL url) {
        new HttpServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, RpcRequest rpcRequest) {
        return new HttpClient().send(url.getHostname(), url.getPort(), rpcRequest);
    }
}
