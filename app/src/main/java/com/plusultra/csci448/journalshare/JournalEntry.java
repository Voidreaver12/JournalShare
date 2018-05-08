package com.plusultra.csci448.journalshare;

import java.util.Date;
import java.util.UUID;

/**
 * JournalEntry is a class to contain a single journal entry and its appropriate data.
 * Each journal entry has a unique UUID.
 *
 * Created by Han on 2/27/18.
 */

public class JournalEntry {
    private UUID mID;
    private Date mDate;
    private String mTitle;
    private String mText;
    private double mLat;
    private double mLon;

    // Create new entries with random UUID
    public JournalEntry() {
        this(UUID.randomUUID());
    }

    public JournalEntry(UUID id) {
        mID = id;
        mDate = new Date();
    }

    // Getters
    public UUID getId() { return mID; }
    public String getTitle() { return mTitle; }
    public Date getDate() { return mDate; }
    public String getText() { return mText; }
    public double getLon() { return mLon; }
    public double getLat() { return mLat; }

    // Setters
    public void setTitle(String title) { mTitle = title; }
    public void setDate(Date date) { mDate = date; }
    public void setText(String text) { mText = text; }
    public void setLon(double l) { mLon = l; }
    public void setLat(double l) { mLat = l; }
}
