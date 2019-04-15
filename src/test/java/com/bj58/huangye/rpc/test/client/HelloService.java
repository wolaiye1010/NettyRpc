package com.bj58.huangye.rpc.test.client;

public interface HelloService {
    String hello(String name);

    String hello(Person person);
}
