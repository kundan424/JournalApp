package com.example.JournalApp.repository;

import com.example.JournalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends MongoRepository<UserEntry, ObjectId> {

  UserEntry findByUsername ( String username);

  void deleteByUsername(String username);
}

