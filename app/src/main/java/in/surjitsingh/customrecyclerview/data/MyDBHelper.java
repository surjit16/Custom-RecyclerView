package in.surjitsingh.customrecyclerview.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import in.surjitsingh.customrecyclerview.Person;

public class MyDBHelper extends SQLiteOpenHelper {

    Context context;
    static  String dbName = "mydb";
    static int version = 1;

    public MyDBHelper(Context context) {
        super(context, dbName, null, version);
        this.context = context;

    }

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create table student (name varchar(30), description varchar(30), date varchar(20), time varchar(20), uid int)";
        sqLiteDatabase.execSQL(query);
    }

    public void insertNew(ContentValues values)
    {

        SQLiteDatabase db = getWritableDatabase();
        db.insert("student", null , values);
    }

    public ArrayList<Person> getData()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from student", null );
        ArrayList<Person> people = new ArrayList<>();
        Person person;
        while (cursor.moveToNext()){
            person = new Person(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
        people.add(person);
        }
        return people;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
