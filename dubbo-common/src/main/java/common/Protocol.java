package common;

import common.request.RpcRequest;

/**
 * @author: changjiale
 * @create: 2019/12/06 14:48
 * @description:
 */
public interface Protocol {

    void start(URL url);

    String send(URL url, RpcRequest rpcRequest);
}
