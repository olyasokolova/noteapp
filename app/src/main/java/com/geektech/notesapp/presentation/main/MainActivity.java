package com.geektech.notesapp.presentation.main;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.data.notes.NotesRepository;
import com.geektech.notesapp.model.NoteEntity;
import com.geektech.notesapp.presentation.addnote.AddNoteActivity;
import com.geektech.notesapp.presentation.intro.IntroActivity;
import com.geektech.notesapp.presentation.main.recycler.NoteItemViewHolder;
import com.geektech.notesapp.presentation.main.recycler.NotesAdapter;
import com.geektech.notesapp.presentation.noteinfo.NoteInfoActivity;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity
    implements NoteItemViewHolder.NoteClickListener {

    private NotesAdapter mAdapter;
    private RecyclerView mRecycler;
    private FloatingActionButton mFab;
    private Button mDeleteAllBtn;

    //region Static

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    //endregion

    //region Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    //endregion

    //region Init

    private void init() {
        mAdapter = new NotesAdapter(new ArrayList<NoteEntity>(), this);

        mRecycler = findViewById(R.id.main_recycler_view);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false));

        mFab = findViewById(R.id.main_fab);
        mDeleteAllBtn = findViewById(R.id.deleteall_btn);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNoteScreen();
            }
        });
        mDeleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.notesStorage.deleteAllNotes();
                onResume();
            }
        });
    }

    //endregion

    //region ViewHolder

    @Override
    public void onClick(int position) {
        NoteEntity note = mAdapter.getNote(position);
        if (note != null) {
            NoteInfoActivity.start(this, note.getId());
        }
    }

    //endregion

    private void openAddNoteScreen() {
        AddNoteActivity.start(this);
    }

    private void loadNotes() {
        mAdapter.setNotes(App.notesStorage.getAllNotes());
    }
}
