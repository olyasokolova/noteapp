package com.geektech.notesapp.data.notes.local;

import android.util.Log;

import com.geektech.notesapp.data.notes.NotesStorage;
import com.geektech.notesapp.data.notes.local.model.NoteMapper;
import com.geektech.notesapp.data.notes.local.model.RNote;
import com.geektech.notesapp.model.NoteEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class NotesLocalStorage implements NotesStorage {


    //region Private

    private Realm getRealm() {
        return Realm.getDefaultInstance();
    }

    private void execute(Realm.Transaction transaction) {
        Realm realm = null;

        try {
            realm = getRealm();

            realm.executeTransaction(transaction);
        } catch (Exception e) {
            //TODO: Log exception
        }  finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    private long getNextId(Class c) {
        Realm realm = null;

        try {
            realm = getRealm();

            String primaryKeyField = realm.getSchema()
                    .get(c.getSimpleName())
                    .getPrimaryKey();

            if (realm.where(c).max(primaryKeyField) == null) {
                return 1;
            }

            return (long)realm.where(c).max(primaryKeyField) + 1;
        } catch (Exception e) {
            return -1;
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    //endregion

    @Override
    public void addNote(final NoteEntity noteEntity) {
        execute(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (noteEntity.getId() == 0) {
                    noteEntity.setId((int) getNextId(RNote.class));
                }

                RNote rNote = NoteMapper.toRNote(noteEntity);
                realm.copyToRealmOrUpdate(rNote);
            }
        });
    }

    @Override
    public NoteEntity getNote(int id) {
        NoteEntity noteEntity = null;
        Realm realm = null;

        try {
            realm = getRealm();

            RNote rNote = realm.where(RNote.class)
                    .equalTo("id", id)
                    .findFirst();

            if (rNote != null) {
                noteEntity = NoteMapper.toNote(rNote);
            }
        } catch (Exception e) {
            Log.e("ololo", e.getMessage(), e);
        }  finally {
            if (realm != null) {
                realm.close();
            }
        }

        return noteEntity;
    }

    @Override
    public Collection<NoteEntity> getAllNotes() {
        ArrayList<NoteEntity> resultNotes = new ArrayList<>();
        Realm realm = null;

        try {
            realm = getRealm();

            RealmResults<RNote> notes = realm.where(RNote.class)
                    .sort("createdAt", Sort.DESCENDING)
                    .findAll();

            for (RNote note : notes) {
                resultNotes.add(NoteMapper.toNote(note));
            }
        } catch (Exception e) {
            Log.e("ololo", e.getMessage(), e);
        }  finally {
            if (realm != null) {
                realm.close();
            }
        }

        return resultNotes;
    }

/*    @Override
    public void deleteNote(final int id) {
        Realm realm = null;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(RNote.class).equalTo("id", id).findFirst().deleteFromRealm();
            }
        });
    }*/

    @Override
    public void deleteNote(final int id) {
        execute(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(RNote.class).equalTo("id", id).findFirst().deleteFromRealm();
            }
        });
    }
    @Override
    public void deleteAllNotes() {
        execute(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(RNote.class).findAll().deleteAllFromRealm();
            }
        });
    }

}
