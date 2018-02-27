package com.plusultra.csci448.journalshare;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ndeibert on 2/27/2018.
 */

public class JournalBook {
    private static JournalBook sJournalBook;
    private List<JournalEntry> mEntries;

    public static JournalBook get(Context context) {
        if (sJournalBook == null) {
            sJournalBook = new JournalBook(context);
        }
        return sJournalBook;
    }

    private JournalBook(Context context) {
        mEntries = new ArrayList<JournalEntry>();
    }

    public JournalEntry getEntry(UUID id) {
        for (JournalEntry entry : mEntries) {
            if (entry.getId().equals(id)) {
                return entry;
            }
        }
        return null;
    }

    public void addEntry(JournalEntry e) { mEntries.add(e); }

    public List<JournalEntry> getEntries() { return mEntries; }
}
