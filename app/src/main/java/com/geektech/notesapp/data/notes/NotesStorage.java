package com.geektech.notesapp.data.notes;

import com.geektech.notesapp.model.NoteEntity;

import java.util.Collection;

public interface NotesStorage {
    void addNote(NoteEntity noteEntity);

    NoteEntity getNote(int id);

    Collection<NoteEntity> getAllNotes();

    void deleteNote(int id);
    void deleteAllNotes();
}
