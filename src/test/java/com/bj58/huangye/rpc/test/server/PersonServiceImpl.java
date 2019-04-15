package com.bj58.huangye.rpc.test.server;

import com.bj58.huangye.rpc.server.RpcService;
import com.bj58.huangye.rpc.test.client.PersonService;
import com.bj58.huangye.rpc.test.client.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luxiaoxun on 2016-03-10.
 */
@RpcService(PersonService.class)
public class PersonServiceImpl implements PersonService {

    @Override
    public List<Person> GetTestPerson(String name, int num) {
        List<Person> persons = new ArrayList<>(num);
        for (int i = 0; i < num; ++i) {
            persons.add(new Person(Integer.toString(i), name));
        }
        return persons;
    }
}
