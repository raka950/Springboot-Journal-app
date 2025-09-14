package com.example.MyFirstProject.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "config_journal_app")
@Getter
@Setter
public class ConfigJournalAppEntry {

    @NonNull
    private String key;
    private String value;






}
