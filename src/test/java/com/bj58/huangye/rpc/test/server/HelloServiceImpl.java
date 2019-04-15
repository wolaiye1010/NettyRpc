package com.bj58.huangye.rpc.test.server;

import com.bj58.huangye.rpc.server.RpcService;
import com.bj58.huangye.rpc.test.client.HelloService;
import com.bj58.huangye.rpc.test.client.Person;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    public HelloServiceImpl(){

    }

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}
