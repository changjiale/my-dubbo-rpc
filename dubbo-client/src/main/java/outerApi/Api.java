package outerApi;

import api.Arithmetic;
import common.protocol.http.HttpClient;
import common.request.RpcRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: changjiale
 * @create: 2019/11/25 17:34
 * @description:
 */
@RestController
public class Api {

    @GetMapping
    public void sum(Integer a, Integer b) {
        HttpClient httpClient = new HttpClient();
        RpcRequest rpcRequest = new RpcRequest(Arithmetic.class.getName(), "sum", new Class[]{Integer.class, Integer.class}, new Object[]{1, 2});
        httpClient.send("localhost", 8080, rpcRequest);
    }

    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        RpcRequest rpcRequest = new RpcRequest(Arithmetic.class.getName(), "sum", new Class[]{Integer.class, Integer.class}, new Object[]{1, 2});
        String result = httpClient.send("localhost", 8087, rpcRequest);
        System.out.println(result);
    }

}
