package com.geektech.notesapp;

import android.app.Application;

import com.geektech.notesapp.data.notes.NotesRepository;
import com.geektech.notesapp.data.notes.NotesStorage;
import com.geektech.notesapp.data.notes.local.NotesLocalStorage;
import com.geektech.util.shared.SharedStorage;
import com.geektech.util.shared.SharedStorageImpl;

import io.realm.Realm;

public class App extends Application {

    private final String SHARED_FILE_NAME = "shared_prefs";

    public static SharedStorage sharedStorage;
    public static NotesStorage notesStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        sharedStorage = new SharedStorageImpl(
                this,
                SHARED_FILE_NAME
        );

        notesStorage = new NotesRepository(
                new NotesLocalStorage()
        );
    }

}
