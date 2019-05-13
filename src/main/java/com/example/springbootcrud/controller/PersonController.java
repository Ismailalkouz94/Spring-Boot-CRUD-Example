package com.example.springbootcrud.controller;

import com.example.springbootcrud.entity.Person;
import com.example.springbootcrud.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    private static final Logger logger = LogManager.getLogger(PersonController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Person create(@RequestParam(value = "name") @Valid String name, @RequestParam(value = "email") String email) {

        Person person = new Person();
        person.setName(name);
        person.setEmail(email);

        return personService.save(person);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Person update(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {

        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setEmail(email);

        return personService.save(person);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "id") Long id) {

        Person person = new Person();
        person.setId(id);
        personService.delete(person);
        return "OK";
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public List<Person> listAll() {
        logger.info("Enter listAll");
        List<Person> list = personService.findAll();
        for (Person item : list) {
            logger.info(">>>> " + item);
        }
        logger.info("Exit listAll");
        return personService.findAll();
    }

}
