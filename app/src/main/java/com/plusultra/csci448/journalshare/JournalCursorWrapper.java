package com.plusultra.csci448.journalshare;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.plusultra.csci448.journalshare.JournalDbSchema.JournalTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ndeibert on 5/7/2018.
 */

public class JournalCursorWrapper extends CursorWrapper {
    public JournalCursorWrapper(Cursor cursor) { super(cursor); }
    public JournalEntry getEntry() {
        String uuid = getString(getColumnIndex(JournalTable.Cols.UUID));
        String title = getString(getColumnIndex(JournalTable.Cols.TITLE));
        String text = getString(getColumnIndex(JournalTable.Cols.TEXT));
        long date = getLong(getColumnIndex(JournalTable.Cols.DATE));

        JournalEntry entry = new JournalEntry(UUID.fromString(uuid));
        entry.setTitle(title);
        entry.setText(text);
        entry.setDate(new Date(date));
        return entry;
    }
}
