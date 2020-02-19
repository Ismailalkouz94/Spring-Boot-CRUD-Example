package com.example.springbootcrud.daolayer;

import com.example.springbootcrud.entity.Person;
import com.sun.xml.internal.stream.StaxErrorReporter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PersonDAO extends JpaRepository<Person,Long> {

    public Person findPersonByEmailAndId(String email,Long id);
    public List<Person> findByEmailContains(String text);
    public int countAllBy();
    public Person findTopByName(String name);
    public Page<Person> findAll(Pageable pageable);
    public List<Person> findAll();
    public List<Person> findByNameNQ(String name);
    public boolean existsPersonByNameAndEmail(String name, String email);
}
