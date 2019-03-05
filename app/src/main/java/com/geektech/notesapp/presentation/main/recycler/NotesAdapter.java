package com.geektech.notesapp.presentation.main.recycler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;

import java.util.ArrayList;
import java.util.Collection;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<NoteEntity> mNotes;
    private NoteItemViewHolder.NoteClickListener mListener;

    public NotesAdapter(
            ArrayList<NoteEntity> notes,
            NoteItemViewHolder.NoteClickListener listener) {
        this.mNotes = notes;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note, viewGroup, false);

        return new NoteItemViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof NoteItemViewHolder) {
            ((NoteItemViewHolder) viewHolder).onBind(mNotes.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void setNotes(Collection<NoteEntity> notes) {
        mNotes.clear();
        mNotes.addAll(notes);
        notifyDataSetChanged();
    }

    @Nullable
    public NoteEntity getNote(int position) {
        if (mNotes.size() > position && position >= 0) {
            return mNotes.get(position);
        } else {
            return null;
        }
    }
}
