package com.plusultra.csci448.journalshare;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Han on 2/27/18.
 */

public class JournalEntry {
    private UUID mID;
    private Date mDate;
    private String mTitle;
    private String mText;

    public JournalEntry() {
        mID = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mID;
    }

    public String getTitle() { return mTitle; }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getText() { return mText; }

    public void setText(String text) { mText = text; }

    public void setTitle(String title) { mTitle = title; }
}
