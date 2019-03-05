package com.geektech.notesapp.presentation.addnote;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText mTitleInput, mDescriptionInput;
    private Button mSubmitButton;

    public static void start(Context context) {
        context.startActivity(new Intent(context, AddNoteActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        init();
    }

    private void init() {
        mTitleInput = findViewById(R.id.add_note_title_input);
        mDescriptionInput = findViewById(R.id.add_note_description_input);

        mSubmitButton = findViewById(R.id.add_note_submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
    }

    private void addNote() {
        NoteEntity note = new NoteEntity();
        note.setTitle(mTitleInput.getText().toString());
        note.setDescription(mDescriptionInput.getText().toString());

        App.notesStorage.addNote(note);
        finish();
    }
}