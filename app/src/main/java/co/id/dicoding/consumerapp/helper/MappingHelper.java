package co.id.dicoding.consumerapp.helper;

import android.database.Cursor;

import java.util.ArrayList;

import co.id.dicoding.consumerapp.entity.NoteItem;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static co.id.dicoding.consumerapp.db.DatabaseContract.NoteColumns.DATE;
import static co.id.dicoding.consumerapp.db.DatabaseContract.NoteColumns.DESCRIPTION;
import static co.id.dicoding.consumerapp.db.DatabaseContract.NoteColumns.TITLE;

public class MappingHelper {
    public static ArrayList<NoteItem> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<NoteItem> notesList = new ArrayList<>();
        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(_ID));
            String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(TITLE));
            String description = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DESCRIPTION));
            String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DATE));
            notesList.add(new NoteItem(id, title, description, date));
        }
        return notesList;
    }
}
