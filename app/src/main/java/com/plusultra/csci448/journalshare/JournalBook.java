package com.plusultra.csci448.journalshare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.plusultra.csci448.journalshare.JournalDbSchema.JournalTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ndeibert on 2/27/2018.
 */

public class JournalBook {
    private static JournalBook sJournalBook;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private int entryBgId;
    private boolean isEntryBgSet = false;

    public static JournalBook get(Context context) {
        if (sJournalBook == null) {
            sJournalBook = new JournalBook(context);
        }
        return sJournalBook;
    }

    private JournalBook(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new JournalBaseHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(JournalEntry entry) {
        ContentValues values = new ContentValues();
        values.put(JournalTable.Cols.UUID, entry.getId().toString());
        values.put(JournalTable.Cols.TITLE, entry.getTitle());
        values.put(JournalTable.Cols.TEXT, entry.getText());
        values.put(JournalTable.Cols.DATE, entry.getDate().getTime());
        return values;
    }

    public JournalEntry getEntry(UUID id) {
        JournalCursorWrapper cursor = queryJournal(
                JournalTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getEntry();
        } finally {
            cursor.close();
        }
    }


    public void addEntry(JournalEntry e) {
        ContentValues values = getContentValues(e);
        mDatabase.insert(JournalTable.NAME, null, values);
    }

    public void deleteEntry(UUID entryId) {
        mDatabase.delete(JournalTable.NAME,
                JournalTable.Cols.UUID + " = ?",
                new String[] { entryId.toString() }
        );
    }

    public void updateEntry(JournalEntry entry) {
        String uuid = entry.getId().toString();
        ContentValues values = getContentValues(entry);
        mDatabase.update(JournalTable.NAME, values,
                JournalTable.Cols.UUID + " = ?",
                new String[] { uuid });
    }

    private JournalCursorWrapper queryJournal(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                JournalTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new JournalCursorWrapper(cursor);
    }

    public List<JournalEntry> getEntries() {
        List<JournalEntry> entries = new ArrayList<>();
        JournalCursorWrapper cursor = queryJournal(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                entries.add(cursor.getEntry());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return entries;
    }

    public void setEntryBgId(int id) {
        entryBgId = id;
        isEntryBgSet = true;
    }
    public int getEntryBgId() { return entryBgId; }
    public boolean isBgSet() { return isEntryBgSet; }
}
