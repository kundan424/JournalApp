package com.example.JournalApp.repository;

import com.example.JournalApp.entity.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntry> getUserForSA() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("username").is("kundan"));
//        query.addCriteria(Criteria.where("email").is(true)); // check whether email is used or not
        query.addCriteria(Criteria.where("email").regex("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")); // for valid email format or regular expression
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true)); // check whether user is opt for sentimentAnalysis or not
        List<UserEntry> userEntries = mongoTemplate.find(query, UserEntry.class);
        return userEntries;
    }
}

// NOTE : these all criteria are combined by "and" expression , for 'OR' we can make object of criteria and
//        then use orOperator method
