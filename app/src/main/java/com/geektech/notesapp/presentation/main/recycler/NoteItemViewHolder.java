package com.geektech.notesapp.presentation.main.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.geektech.notesapp.R;
import com.geektech.notesapp.model.NoteEntity;

public class NoteItemViewHolder extends RecyclerView.ViewHolder {

    private NoteClickListener mListener;
    private TextView mTitle, mDescription, mCreatedAt;

    NoteItemViewHolder(
            @NonNull View itemView,
            NoteClickListener listener
    ) {
        super(itemView);
        mListener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(getAdapterPosition());
            }
        });

        mTitle = itemView.findViewById(R.id.item_note_title);
        mDescription = itemView.findViewById(R.id.item_note_description);
        mCreatedAt = itemView.findViewById(R.id.item_note_created_at);
    }

    void onBind(NoteEntity noteEntity) {
        mTitle.setText(noteEntity.getTitle());
        mDescription.setText(noteEntity.getDescription());
        mCreatedAt.setText(noteEntity.getCreatedAt().toString()); //TODO: Format date
    }

    public interface NoteClickListener {
        void onClick(int position);
    }
}
