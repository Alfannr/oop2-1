package com.phb.crudapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "Santri.db, Ponpes.db", null, 1) {
    companion object{
        private var instance: DBHelper? = null
        @Synchronized
        fun getInstance(ctx: Context) : DBHelper{

            if(instance == null){
                instance = DBHelper(ctx.applicationContext)
            }
            return instance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Santri.TABLE_SANTRI, true,
            Santri.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Santri.NAMA to TEXT,
            Santri.ALAMAT to TEXT,
            Santri.HANDPHONE to TEXT
        )
        db?.createTable(Ponpes.TABLE_PONPES, true,
            Ponpes.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Ponpes.PESANTREN to TEXT,
            Ponpes.ALAMATPONPES to TEXT,
            Ponpes.PENGASUH to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Santri.TABLE_SANTRI, true)
        db?.dropTable(Ponpes.TABLE_PONPES, true)
    }
}

val Context.database : DBHelper
get() = DBHelper.getInstance(applicationContext)