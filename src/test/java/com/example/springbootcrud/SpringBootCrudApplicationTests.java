package com.example.springbootcrud;

import com.example.springbootcrud.entity.Person;
import com.example.springbootcrud.exception.CustomException;
import com.example.springbootcrud.service.PersonService;
import com.example.springbootcrud.util.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCrudApplicationTests {

    private static final Logger logger = LogManager.getLogger(SpringBootCrudApplicationTests.class);

    @Autowired
    private PersonService personService;

    @Before
    @Test
    public void create_usingHTTPRequest() {
        logger.info("------------------------- create_usingHTTPRequest ---------------------------------");
        String responseStr = null;
        try {
            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL + Constants.PERSON_URL + Constants.CREATE).newBuilder();
            RequestBody formBody = new FormEncodingBuilder()
                    .add("name", "ismail")
                    .add("email", (Math.random() * 100) + "@test.com")
                    .build();
            String fullUrl = urlBuilder.build().toString();
            Request request = new Request.Builder()
                    .url(fullUrl)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            responseStr = response.body().string();
            Person person=new Gson().fromJson(responseStr,Person.class);
            logger.info("JSON_response : "+responseStr);
            logger.info("person_response : "+person);
            logger.info("------------------------- create_usingHTTPRequest ---------------------------------");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

//    @Test
//    public void findPersonByEmailAndId() {
//        logger.info("------------------------- findPersonByEmailAndId ---------------------------------");
//        try {
//            Person person = personService.findPersonByEmailAndId("62.64864083024191@test.com", 1L);
//            logger.info(person);
//            Assert.assertEquals(person.getEmail(), "62.64864083024191@test.com");
//            logger.info("------------------------- findPersonByEmailAndId ---------------------------------");
//        } catch (Exception e) {
//            throw new CustomException(e.getMessage());
//        }
//
//    }

    @Test
    public void findByEmailContains() {
        logger.info("------------------------- findByEmailContains ---------------------------------");
        List<Person> person = personService.findByEmailContains("com");
        person.forEach(x -> logger.info(x));
//        Assert.assertEquals(person.size(), 12);
        logger.info("------------------------- findByEmailContains ---------------------------------");
    }

    @Test
    public void countAll() {
        logger.info("------------------------- countAll ---------------------------------");
        logger.info(personService.countAllBy());
        logger.info("------------------------- countAll ---------------------------------");
    }

    @Test
    public void findDistinctByName() {
        logger.info("------------------------- findDistinctByName ---------------------------------");
        Person person = personService.findTopByName("ismail");
        logger.info(person);
        logger.info("------------------------- findDistinctByName ---------------------------------");
//        Assert.assertEquals(person.size(),2);
    }

    @Test
    public void findAllWithPageable() {
        logger.info("------------------------- findAllWithPageable ---------------------------------");
        Pageable firstPageWithTwoElements = PageRequest.of(0, 5);
        personService.findAll(firstPageWithTwoElements).forEach(x -> logger.info(x));
        logger.info("------------------------- findAllWithPageable ---------------------------------");
    }

    @Test
    public void findAllWithPageableAndSort() {
        logger.info("------------------------- findAllWithPageableAndSort ---------------------------------");
        Pageable firstPageWithTwoElements = PageRequest.of(0, 5, Sort.by("id"));
        personService.findAll(firstPageWithTwoElements).forEach(x -> logger.info(x));
        logger.info("------------------------- findAllWithPageableAndSort ---------------------------------");
    }

    @Test
    public void findAll() {
        logger.info("------------------------- findAll ---------------------------------");
        personService.findAll().forEach(x -> logger.info(x));
        logger.info("------------------------- findAll ---------------------------------");
    }

    @Test
    public void findByName_namedQuery() {
        logger.info("------------------------- findByName_namedQuery ---------------------------------");
        personService.findByNameNQ("ismail").forEach(x -> logger.info(x));
        logger.info("------------------------- findByName_namedQuery ---------------------------------");
    }

    @Test
    public void findAll_HTTP_JSON() {
        logger.info("------------------------- findAll_HTTP_JSON ---------------------------------");
        String responseStr = null;
        try {
            OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL + Constants.PERSON_URL + Constants.FIND_ALL).newBuilder();
            String fullUrl = urlBuilder.build().toString();
            Request request = new Request.Builder()
                    .url(fullUrl)
                    .build();
            Response response = client.newCall(request).execute();
            responseStr = response.body().string();

            Type listType = new TypeToken<List<Person>>() {
            }.getType();
            List<Person> personList = new Gson().fromJson(responseStr, listType);
            logger.info("JSON_response : " + responseStr);
            logger.info("personList : " + personList);

            logger.info("------------------------- findAll_HTTP_JSON ---------------------------------");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

}
