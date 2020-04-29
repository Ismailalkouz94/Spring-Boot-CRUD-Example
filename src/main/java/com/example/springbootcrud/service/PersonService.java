package com.example.springbootcrud.service;

import com.example.springbootcrud.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    public Person findPersonByEmailAndId(String email, Long id);
    public List<Person> findByEmailContains(String text);
    public int countAllBy();
    public Person findTopByName(String name);
    public List<Person> findAll();
    public Page<Person> findAll(Pageable pageable);
    public void delete(Person person);
    public Person save(Person person);
    public List<Person> findByNameNQ(String name);
    public Person findById(Long id);
}
