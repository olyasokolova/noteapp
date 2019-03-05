package com.geektech.notesapp.presentation.editnote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditTitle, mEditDesc;
    Button mSaveEditedBtn;

    private static final String EXTRA_ID = "id";
    private static int noteID;

    public static void start(Context context, int id) {
        context.startActivity(intent(context, id));
    }

    public static Intent intent(Context context, int id) {
        Intent intent = new Intent(context, EditNoteActivity.class);

        intent.putExtra(EXTRA_ID, id);
        noteID = id;

        return intent;
    }


    private void init(){
        mEditDesc = findViewById(R.id.note_edit_description);
        mEditTitle = findViewById(R.id.note_edit_title);
        mSaveEditedBtn = findViewById(R.id.save_edited);
        mSaveEditedBtn.setOnClickListener(this);

        loadNote();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        init();
    }


    private void loadNote() {
        NoteEntity note = App.notesStorage
                .getNote(getIntent().getIntExtra(EXTRA_ID, -1));

        if (note != null) {
            mEditTitle.setText(note.getTitle());
            mEditDesc.setText(note.getDescription());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_edited:
                saveEditedNote();
                finish();
                break;
        }

    }

    private void saveEditedNote() {
        NoteEntity note = new NoteEntity();
        note.setId(noteID);
        note.setTitle(mEditTitle.getText().toString());
        note.setDescription(mEditDesc.getText().toString());

        App.notesStorage.addNote(note);
    }
}
