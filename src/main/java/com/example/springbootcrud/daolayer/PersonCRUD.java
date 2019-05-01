package com.example.springbootcrud.daolayer;

import com.example.springbootcrud.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonCRUD extends CrudRepository<Person,Long> {

    public Person findPersonByEmailAndId(String email,Long id);
    public List<Person> findByEmailContains(String text);
    public int countAllBy();
    public Person findTopByName(String name);

}
