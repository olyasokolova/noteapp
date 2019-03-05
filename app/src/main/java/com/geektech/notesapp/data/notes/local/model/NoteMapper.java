package com.geektech.notesapp.data.notes.local.model;

import com.geektech.notesapp.model.NoteEntity;

public class NoteMapper {
    public static RNote toRNote(NoteEntity noteEntity) {
        RNote rNote = new RNote();

        rNote.setTitle(noteEntity.getTitle());
        rNote.setDescription(noteEntity.getDescription());
        rNote.setCreatedAt(noteEntity.getCreatedAt());
        rNote.setId(noteEntity.getId());

        return rNote;
    }

    public static NoteEntity toNote(RNote rNote) {
        NoteEntity noteEntity = new NoteEntity();

        noteEntity.setTitle(rNote.getTitle());
        noteEntity.setDescription(rNote.getDescription());
        noteEntity.setCreatedAt(rNote.getCreatedAt());
        noteEntity.setId((int) rNote.getId());

        return noteEntity;
    }
}
