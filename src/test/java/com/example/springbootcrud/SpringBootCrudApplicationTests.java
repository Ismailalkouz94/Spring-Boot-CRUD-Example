package com.example.springbootcrud;

import com.example.springbootcrud.daolayer.PersonCRUD;
import com.example.springbootcrud.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCrudApplicationTests {

	@Autowired
	private PersonCRUD personCRUD;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findPersonByEmailAndId() {
		Person person=personCRUD.findPersonByEmailAndId("ialkouz@fact.com.jo", 3L);
		System.out.println(person);
		Assert.assertEquals(person.getEmail(),"ialkouz@fact.com.jo");
	}

    @Test
    public void findByEmailContains() {
        List<Person> person=personCRUD.findByEmailContains("com");
        person.forEach(System.out::println);
        Assert.assertEquals(person.size(),2);
    }

    @Test
    public void countAll() {
        System.out.println(personCRUD.countAllBy());
    }

    @Test
    public void findDistinctByName() {
        Person person=personCRUD.findTopByName("ismail alkouz");
        System.out.println(person.toString());
//        Assert.assertEquals(person.size(),2);
    }

}
