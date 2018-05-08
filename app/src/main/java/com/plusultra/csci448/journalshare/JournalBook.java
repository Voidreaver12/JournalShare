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
 * JournalBook is used as a singleton to contain your local journal entries.
 * This is directly connected to the local database.
 *
 * Created by ndeibert on 2/27/2018.
 */

public class JournalBook {

    private static JournalBook sJournalBook;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    // Get static instance of JournalBook
    public static JournalBook get(Context context) {
        if (sJournalBook == null) {
            sJournalBook = new JournalBook(context);
        }
        return sJournalBook;
    }

    // Set context and get database
    private JournalBook(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new JournalBaseHelper(mContext).getWritableDatabase();
    }

    // Database values for a JournalEntry
    private static ContentValues getContentValues(JournalEntry entry) {
        ContentValues values = new ContentValues();
        values.put(JournalTable.Cols.UUID, entry.getId().toString());
        values.put(JournalTable.Cols.TITLE, entry.getTitle());
        values.put(JournalTable.Cols.TEXT, entry.getText());
        values.put(JournalTable.Cols.DATE, entry.getDate().getTime());
        values.put(JournalTable.Cols.LAT, entry.getLat());
        values.put(JournalTable.Cols.LON, entry.getLon());
        return values;
    }

    // Get a single journal entry from the database by UUID
    public JournalEntry getEntry(UUID id) {
        JournalCursorWrapper cursor = queryJournal(
                JournalTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            // No matches, return null
            if (cursor.getCount() == 0) {
                return null;
            }
            // Matches, return the first one
            cursor.moveToFirst();
            return cursor.getEntry();
        } finally {
            cursor.close();
        }
    }

    // Add a single journal entry to the database
    public void addEntry(JournalEntry e) {
        ContentValues values = getContentValues(e);
        mDatabase.insert(JournalTable.NAME, null, values);
    }

    // Delete an entry from database by UUID
    public void deleteEntry(UUID entryId) {
        mDatabase.delete(JournalTable.NAME,
                JournalTable.Cols.UUID + " = ?",
                new String[] { entryId.toString() }
        );
    }

    // Update an entry in database by UUID
    public void updateEntry(JournalEntry entry) {
        String uuid = entry.getId().toString();
        ContentValues values = getContentValues(entry);
        mDatabase.update(JournalTable.NAME, values,
                JournalTable.Cols.UUID + " = ?",
                new String[] { uuid });
    }

    // For querying the database in getEntry and getEntries
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

    // Get a list of all entries in database
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

}
