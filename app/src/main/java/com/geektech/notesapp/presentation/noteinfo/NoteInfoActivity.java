package com.geektech.notesapp.presentation.noteinfo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;
import com.geektech.notesapp.presentation.editnote.EditNoteActivity;

import java.text.SimpleDateFormat;

public class NoteInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTitle, mDescription, mCreatedAt;
    private Button mEditBtn, mDeleteBtn;

    //region Static

    private static final String EXTRA_ID = "id";
    private static int noteID;

    public static void start(Context context, int id) {
        context.startActivity(intent(context, id));
    }

    public static Intent intent(Context context, int id) {
        Intent intent = new Intent(context, NoteInfoActivity.class);

        intent.putExtra(EXTRA_ID, id);
        noteID = id;

        return intent;
    }

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_info);

        init();
    }

    private void init() {
        mTitle = findViewById(R.id.note_info_title);
        mDescription = findViewById(R.id.note_info_description);
        mCreatedAt = findViewById(R.id.note_info_created_at);
        mEditBtn = findViewById(R.id.edit_btn);
        mDeleteBtn = findViewById(R.id.delete_btn);
        mEditBtn.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);

        loadNote();
    }

    private void loadNote() {
        NoteEntity note = App.notesStorage
                .getNote(getIntent().getIntExtra(EXTRA_ID, -1));

        if (note != null) {
            mTitle.setText(note.getTitle());
            mDescription.setText(note.getDescription());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            mCreatedAt.setText(dateFormat.format(note.getCreatedAt()));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_btn:
            EditNoteActivity.start(this, noteID);
            finish();
            break;
            case  R.id.delete_btn:
                App.notesStorage.deleteNote(noteID);
                finish();
                break;
        }

    }
}
