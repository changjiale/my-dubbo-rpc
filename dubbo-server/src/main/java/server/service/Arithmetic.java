package server.service;

import common.annotation.RpcAnnotation;

/**
 * @author: changjiale
 * @create: 2019/11/25 17:58
 * @description:
 */
@RpcAnnotation(api.Arithmetic.class)
public class Arithmetic {

    public String sum(Integer a, Integer b) {
        System.out.println("sum:" + (a+b));
        return "rpc return sum:" + (a+b);
    }
}
