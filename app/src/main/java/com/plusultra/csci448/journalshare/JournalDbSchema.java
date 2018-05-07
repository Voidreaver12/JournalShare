package com.plusultra.csci448.journalshare;

/**
 * Created by ndeibert on 5/7/2018.
 */

public class JournalDbSchema {
    public static final class JournalTable {
        public static final String NAME = "journals";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String TEXT = "text";
            public static final String DATE = "date";
        }
    }
}
