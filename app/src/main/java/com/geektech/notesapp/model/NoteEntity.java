package com.geektech.notesapp.model;

import java.util.Date;

public class NoteEntity {
    private int id;
    private String title;
    private String description;
    private Date createdAt;

    //region Constructors

    public NoteEntity(int id, String title, String description, Date createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
    }

    public NoteEntity() {
        this.id = 0;
        this.title = "Title";
        this.description = "Description";
        this.createdAt = new Date();
    }

    //endregion

    //region Getters/Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    //endregion
}
