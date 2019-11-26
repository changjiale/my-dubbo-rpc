package server.service;

import api.Api;
import common.annotation.RpcAnnotation;

/**
 * @author: changjiale
 * @create: 2019/11/25 17:58
 * @description:
 */
@RpcAnnotation(Api.class)
public class Arithmetic {

    public void sum(Integer a, Integer b) {
        System.out.println("sum:" + (a+b));
    }
}
