package in.ac.srmuniv.sqlitetutorial;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SRMDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_SRMQuerys };

    public SRMDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public SRMQuerys createSRMQuerys(String SRMQuerys) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_SRMQuerys, SRMQuerys);
        long insertId = database.insert(MySQLiteHelper.TABLE_SRMQuerys, null,
                values);
        // To show how to query
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SRMQuerys,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        return cursorToSRMQuerys(cursor);
    }

    public void deleteSRMQuerys(SRMQuerys SRMQuerys) {
        long id = SRMQuerys.getId();
        System.out.println("SRMQuerys deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_SRMQuerys, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<SRMQuerys> getAllSRMQuerys() {
        List<SRMQuerys> SRMQueryss = new ArrayList<SRMQuerys>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SRMQuerys,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SRMQuerys SRMQuerys = cursorToSRMQuerys(cursor);
            SRMQueryss.add(SRMQuerys);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return SRMQueryss;
    }

    private SRMQuerys cursorToSRMQuerys(Cursor cursor) {
        SRMQuerys SRMQuerys = new SRMQuerys();
        SRMQuerys.setId(cursor.getLong(0));
        SRMQuerys.setquery(cursor.getString(1));
        return SRMQuerys;
    }

}