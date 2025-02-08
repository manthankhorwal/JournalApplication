package com.MarvelMan.JournalApplication.repository;

import com.MarvelMan.JournalApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate;
public List<User> getUserForSA(){
    Query q=new Query();
    q.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
    q.addCriteria(Criteria.where("sentimentAnalysis").exists(true));

    List<User> users = mongoTemplate.find(q, User.class);
    return users;

}
}

