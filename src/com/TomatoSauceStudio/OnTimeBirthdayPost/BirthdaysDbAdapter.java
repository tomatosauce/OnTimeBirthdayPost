/*
 * Copyright (C) 2011 Deepika Padmanabhan
 * Based on the Db adapter in the Android Notepad sample.
 * This file is part of OnTimeBirthdayPost.
 * Contact the developers at tsaucestudio@gmail.com

 * OnTimeBirthdayPost is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * OnTimeBirthdayPost is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with OnTimeBirthdayPost.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.TomatoSauceStudio.OnTimeBirthdayPost;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
//import android.util.Log;

/**
 * Simple birthdays database access helper class.
 */
public class BirthdaysDbAdapter {

    public static final String KEY_NAME = "name";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_BIRTHDAY_FORMATTED = "birthday_f";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_FBID = "fbid";
    public static final String KEY_ID = "_id";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
        "create table birthdays (_id integer primary key autoincrement, name text unique not null, birthday text, birthday_f text, location text, fbid text not null);";

    /**
     * Important: You must provide a Db & Table name here for this app to work.
     * I am removing the ones here for additional security of existing installs.
     */
    private static final String DATABASE_NAME = "";
    private static final String DATABASE_TABLE = "";
    private static final int DATABASE_VERSION = 5;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
            //        + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS birthdays");
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     */
    public BirthdaysDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the birthdays database. If it cannot be opened, try to create a new
     * instance of the database.
     */
    public BirthdaysDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    /**
     * Create a new friend record using the data provided.
     */
    public long createFriendRecord(String fname, String bday, String bday_f, String loc, String fbid) {
    	
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, fname);
        initialValues.put(KEY_BIRTHDAY, bday);
        initialValues.put(KEY_BIRTHDAY_FORMATTED, bday_f);
        initialValues.put(KEY_LOCATION, loc);
        initialValues.put(KEY_FBID, fbid);

        return mDb.replace(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the friend record with the given id
     */
    public boolean deleteNote(String name) {

        return mDb.delete(DATABASE_TABLE, KEY_NAME + "=" + name, null) > 0;
    }

    /**
     * Return a Cursor over the list of all friends in the database
     */
    public Cursor fetchAllBirthdays() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_NAME,
                KEY_BIRTHDAY_FORMATTED, KEY_LOCATION}, null, null, null, null, null);
    }
    
    /**
     * Get a row count.
     */
    public int getRowCount() {
    	SQLiteStatement s = mDb.compileStatement("SELECT count(*) FROM birthdays");
    	return (int) s.simpleQueryForLong();
    }
    /**
     * Return a Cursor over the list of all friends with a birthday
     * greater than startDate and less than endDate
     */
    public Cursor fetchBirthdays(String startDate, String endDate) {
    	Cursor c = mDb.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_NAME,
    			KEY_BIRTHDAY_FORMATTED, KEY_LOCATION, KEY_FBID}, KEY_BIRTHDAY + ">="
                + startDate + " AND " + KEY_BIRTHDAY + "<=" + endDate, null, null, null, KEY_BIRTHDAY);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
}
