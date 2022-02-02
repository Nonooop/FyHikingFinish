package com.nopi.fyhiking

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "dbData.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "tbUser"
        public const val ID_COL = "id"
        public const val NAME_COL = "name"
        public const val EMAIL_COL = "email"
        public const val PASS_COL = "pass"
        public var getPass = "getpass"
        public var getAge = "getage"
        public var IDRow: Long =0
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " +TABLE_NAME+ "(" + ID_COL +
                " INTEGER PRIMARY KEY, " +
                NAME_COL + " TEXT," +
                EMAIL_COL + " TEXT," +
                PASS_COL + " TEXT" + ")")
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }

    fun addData(name: String?, email:String?, pass: String?) : Boolean{
        val values = ContentValues()
        values.put(NAME_COL, name)
        values.put(EMAIL_COL, email)
        values.put(PASS_COL, pass)
        val db = this.writableDatabase
        var cursor: Cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME+
                " WHERE NAME = '$name'", null)
        if(cursor.moveToFirst()){
            return false
        }
        else{
            db.insert(TABLE_NAME,null, values)
            return true
        }
    }

    fun LoginDatas(name: String?, email: String?, pass: String?): Boolean {
        val values = ContentValues()
        values.put(NAME_COL, name)
        values.put(EMAIL_COL, email)
        values.put(PASS_COL, pass)
        val db = this.writableDatabase
        var cursor: Cursor = db.rawQuery(
            "Select * from " + TABLE_NAME + " WHERE (email = '$email' OR name='$name')"+" AND pass ='$pass'",
            null
        )
        if (cursor.count>0) {
            return true;
        } else {
            return false;
        }
    }
}