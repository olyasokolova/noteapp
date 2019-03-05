package com.geektech.notesapp.data.notes.local.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RNote extends RealmObject {
    @PrimaryKey
    private long id;
    private String title;

    private String description;

    private Date createdAt;

    public RNote() {
        this.id = 0;
        this.title = "Title";
        this.description = "Description";
        this.createdAt = new Date();
    }

    public RNote(long id, String title, String description, Date createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
