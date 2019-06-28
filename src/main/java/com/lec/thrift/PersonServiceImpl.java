package com.lec.thrift;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * 如何生成Thrift类:
 * thrift --gen java src/thrift/data.thrift
 *
 * @author zhwanwan
 * @create 2019-06-21 8:24 AM
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("Got Client Param " + username);
        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("save person....");
        System.out.println("Got Client Param: " + person);
    }
}
