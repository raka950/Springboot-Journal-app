package com.example.MyFirstProject.Repository;

import com.example.MyFirstProject.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.jar.JarEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {




}
