package com.example.springbootcrud.controller;

import com.example.springbootcrud.daolayer.PersonCRUD;
import com.example.springbootcrud.entity.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);
    @Autowired
    private PersonCRUD personCRUD;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Person create(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {

        Person person = new Person();
        person.setName(name);
        person.setEmail(email);

        return personCRUD.save(person);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Person update(@RequestParam(value = "id") Long id,@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {

        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setEmail(email);

        return personCRUD.save(person);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "id") Long id) {

        Person person = new Person();
        person.setId(id);
         personCRUD.delete(person);
         return "OK";
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public List<Person> listAll() {
        logger.info("Enter listAll");
        List<Person> list=(List<Person>) personCRUD.findAll();
        for (Person item:list) {
            logger.info(">>>> "+item);
        }

        logger.info("Exit create");
        return (List<Person>) personCRUD.findAll();
    }


}
