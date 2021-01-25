package co.id.dicoding.consumerapp;

import android.database.Cursor;

public interface LoadNotesCallback {
    void postExecute(Cursor notes);
}
