package com.plusultra.csci448.journalshare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.plusultra.csci448.journalshare.JournalDbSchema.JournalTable;

/**
 * JournalBaseHelper is used to create the local database for journal entries.
 *
 * Created by ndeibert on 5/7/2018.
 */

public class JournalBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "journalBase.db";

    public JournalBaseHelper(Context context) { super(context, DATABASE_NAME, null, VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + JournalTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                JournalTable.Cols.UUID + ", " +
                JournalTable.Cols.TITLE + ", " +
                JournalTable.Cols.TEXT + ", " +
                JournalTable.Cols.DATE + ", " +
                JournalTable.Cols.LAT + ", " +
                JournalTable.Cols.LON + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
