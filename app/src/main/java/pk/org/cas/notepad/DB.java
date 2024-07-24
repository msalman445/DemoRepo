package pk.org.cas.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
    private static DB instance;
    public static final String DB_NAME = "NOTEPAD";
    public static final int DB_VERSION = 3;

    private DB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DB getInstance(Context context){
        if(instance == null){
            instance = new DB(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Notes.CREATE_TABLE);

        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if((oldVersion != newVersion)){
            db.execSQL(Notes.DROP_TABLE);
            db.execSQL(Notes.CREATE_TABLE);

            db.execSQL(Notes.DROP_TABLE);
            db.execSQL(Notes.CREATE_TABLE);
        }
    }

    // Crude Operations of Note.
    public boolean insertNote(Notes note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Notes.COL_TITLE, note.getTitle());
        contentValues.put(Notes.COL_NOTE, note.getNote());
        long rowID;
        try {
            rowID = db.insert(Notes.TABLE_NAME, null, contentValues);
        }catch (Exception ex){
            return false;
        }
        return rowID != -1;
    }

    public boolean updateNote(Notes note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Notes.COL_TITLE, note.getTitle());
        contentValues.put(Notes.COL_NOTE, note.getNote());
        long rowID;
        try{
            rowID = db.update(Notes.TABLE_NAME, contentValues, Notes.COL_NOTE_ID+"= ?", new String[]{String.valueOf(note.getNoteId())});
        }catch (Exception ex){
            return false;
        }
        return rowID != -1;
    }

    public boolean deleteNote(int noteId){
        SQLiteDatabase db = getWritableDatabase();
        long rowId;
        try {
            rowId = db.delete(Notes.TABLE_NAME, Notes.COL_NOTE_ID+" = ? ", new String[]{String.valueOf(noteId)});
        }catch (Exception ex){
            return false;
        }
        return rowId != -1;
    }

    public List<Notes> fetchNotes(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(Notes.SELECT_ALL_NOTES, null);
        List<Notes> notes = new ArrayList<>(cursor.getCount());
        if(cursor.moveToFirst()){
            do{
                Notes note = new Notes();
                int index = cursor.getColumnIndex(Notes.COL_NOTE_ID);
                note.setNoteId(cursor.getInt(index));
                index = cursor.getColumnIndex(Notes.COL_TITLE);
                note.setTitle(cursor.getString(index));
                index = cursor.getColumnIndex(Notes.COL_NOTE);
                note.setNote(cursor.getString(index));
                notes.add(note);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }
}
