package com.example.MyFirstProject.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
@Getter
@Setter
public class ConfigJournalAppEntry {

    @NonNull
    private String key;
    private String value;
}
