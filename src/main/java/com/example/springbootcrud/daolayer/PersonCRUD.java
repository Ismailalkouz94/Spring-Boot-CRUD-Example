package com.example.springbootcrud.daolayer;

import com.example.springbootcrud.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonCRUD extends CrudRepository<Person,Long> {
}
