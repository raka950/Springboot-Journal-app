package com.example.MyFirstProject.Repository;

import com.example.MyFirstProject.entity.JournalEntry;
import com.example.MyFirstProject.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

    void deleteByUserName(String username);

   User findByUserName(String username);





}
