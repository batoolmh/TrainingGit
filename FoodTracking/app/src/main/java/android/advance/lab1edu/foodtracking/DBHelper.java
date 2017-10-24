package android.advance.lab1edu.foodtracking;

/**
 * Created by Batool on 10/12/2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "food.db";
    private static final int DATABASE_VERSION = 8;
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE= "age";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CAL = "calories";
    public static final String COLUMN_COUNT = "count";


    public static final String COLUMN_FQ = "fq";
    public static final String COLUMN_SQ = "sq";
    public static final String COLUMN_THQ = "thq";
    public static final String COLUMN_FQA = "fqa";
    public static final String COLUMN_SQA= "sqa";
    public static final String COLUMN_THQA = "thqa";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_HOURS = "hours";


    public static final String TABLE_NAMEF = "food";
    public static final String COLUMN_IDF = "_id";
    public static final String COLUMN_NAMEF = "name";
    public static final String COLUMN_CALORIE= "calorie";

    public static final String TABLE_NAMES = "sport";
    public static final String COLUMN_IDS = "_id";
    public static final String COLUMN_NAMES = "name";
    public static final String COLUMN_CALORIES= "calories";

    public static final String TABLE_NAMEHS = "sporthistory";
    public static final String COLUMN_IDHS = "_id";
    public static final String COLUMN_NAMEHS = "name";

    public static final String TABLE_NAMEHF = "foodhistory";
    public static final String COLUMN_IDHF = "_id";
    public static final String COLUMN_NAMEHF = "name";
    public static final String COLUMN_USERIDF = "usrid";


    //**************************************************************************************

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    public DBHelper(Context context, int dataBaseVersion) {
        super(context, DATABASE_NAME , null, dataBaseVersion);
    }
    //**************************************************************************************
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME+ "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, "+ COLUMN_FQ+ " TEXT, "+COLUMN_FQA+ " TEXT, "+ COLUMN_SQ +" TEXT, "+COLUMN_SQA +" TEXT, "+ COLUMN_THQ + " TEXT, "+ COLUMN_THQA + " TEXT, "+COLUMN_PASSWORD +" TEXT, "+COLUMN_EMAIL +" TEXT, "+ COLUMN_GENDER+" TEXT, "+COLUMN_WEIGHT+ " INTEGER, "+ COLUMN_HEIGHT+ " INTEGER, "+ COLUMN_AGE+ " INTEGER, "+ COLUMN_HOURS+ " INTEGER, "+COLUMN_CAL+ " INTEGER, "+ COLUMN_COUNT+ " INTEGER)" );
        db.execSQL("CREATE TABLE " + TABLE_NAMES + "(" + COLUMN_IDS + " INTEGER PRIMARY KEY, " + COLUMN_NAMES + " TEXT, "+COLUMN_CALORIES +" INTEGER)" );
        db.execSQL("CREATE TABLE " + TABLE_NAMEF + "(" + COLUMN_IDF + " INTEGER PRIMARY KEY, " + COLUMN_NAMEF + " TEXT, "+COLUMN_CALORIE +" INTEGER)" );
        db.execSQL("CREATE TABLE " + TABLE_NAMEHF + "(" + COLUMN_IDHF + " INTEGER PRIMARY KEY, " + COLUMN_NAMEHF +  " TEXT, "+COLUMN_USERIDF +" INTEGER)");
  //      db.execSQL("CREATE TABLE " + TABLE_NAMEHS + "(" + COLUMN_IDHS + " INTEGER PRIMARY KEY, " + COLUMN_NAMEHS + " TEXT) ");


    }
    //**************************************************************************************
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEHF);
        onCreate(db);
    }
    //**************************************************************************************
    public boolean insertUser(String name, String fq, String fqa, String sq, String sqa, String thq, String thqa, String password, String email,String gender, int weight, int height, int age){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_FQ, fq);
        contentValues.put(COLUMN_FQA, fqa);
        contentValues.put(COLUMN_SQ, sq);
        contentValues.put(COLUMN_SQA, sqa);
        contentValues.put(COLUMN_THQ, thq);
        contentValues.put(COLUMN_THQA, thqa);
        contentValues.put(COLUMN_PASSWORD, password);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_GENDER, gender);
        contentValues.put(COLUMN_WEIGHT, weight);
        contentValues.put(COLUMN_HEIGHT, height);
        contentValues.put(COLUMN_AGE, age);
        contentValues.put(COLUMN_CAL, 0);
        contentValues.put(COLUMN_HOURS, 0);
        contentValues.put(COLUMN_COUNT,0);

        long re= db.insert(TABLE_NAME, null, contentValues);
        if(re==-1) {
            return false;
        }
        else
        {
            return true;
        }
    }
    //**************************************************************************************
    public boolean insertSport(String name, int calorie) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMES, name);
        contentValues.put(COLUMN_CALORIES, calorie);

        long re= db.insert(TABLE_NAMES, null, contentValues);
        if(re==-1) {
            return false;
        }
        else
        {
            return true;
        }
    }
    //**************************************************************************************
    public boolean insertFood(String name, int calorie) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMEF, name);
        contentValues.put(COLUMN_CALORIE, calorie);

        long re= db.insert(TABLE_NAMEF, null, contentValues);
        if(re==-1) {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean insertFoodHistory(String name, int id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMEHF, name);
        contentValues.put(COLUMN_USERIDF, id);

        long re= db.insert(TABLE_NAMEHF, null, contentValues);
        if(re==-1) {
            return false;
        }
        else
        {
            return true;
        }
    }
    //**************************************************************************************
    public boolean insertSportHistory(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMEHS, name);

        long re= db.insert(TABLE_NAMEHF, null, contentValues);
        if(re==-1) {
            return false;
        }
        else
        {
            return true;
        }
    }
    //**************************************************************************************
    public boolean updateUser(Integer id, String name, String fq, String sq, String thq, String email, String gender, int weight, int height, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_FQA, fq);
        contentValues.put(COLUMN_SQA, sq);
        contentValues.put(COLUMN_THQA, thq);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_GENDER, gender);
        contentValues.put(COLUMN_WEIGHT, weight);
        contentValues.put(COLUMN_HEIGHT, height);
        contentValues.put(COLUMN_AGE, age);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    //**************************************************************************************

    public boolean updateCal(int id, int val){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CAL,val);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateHo(int id, int val){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_HOURS,val);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateCount(int id, int val){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COUNT, val);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    //**************************************************************************************
    public Cursor getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?", new String[] { Integer.toString(id) } );
        return res;
    }

    public Cursor getSport(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TABLE_NAMES + " WHERE " + COLUMN_IDS + "=?", new String[] { Integer.toString(id) } );
        return res;
    }

    public Cursor getFood(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TABLE_NAMEF + " WHERE " + COLUMN_IDF + "=?", new String[] { Integer.toString(id) } );
        return res;
    }

    public Cursor getfHis(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TABLE_NAMEHF + " WHERE " + COLUMN_USERIDF+ "=?", new String[] { Integer.toString(id) } );
        return res;
    }

    //**************************************************************************************
    public Cursor getAllFoods() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TABLE_NAMEF, null );
        return res;
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TABLE_NAME, null );
        return res;
    }

    public Cursor getAllSports() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TABLE_NAMES, null );
        return res;
    }
    //**************************************************************************************
    public Cursor isExist (String name, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=?"+ " AND "+COLUMN_PASSWORD+"=?",new String[] {name,password});
        return res;
    }
    //**************************************************************************************
    public Cursor isExistS (String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAMES + " WHERE " + COLUMN_NAMES + "=?",new String[] {name});
        return res;
    }

    public Cursor isExistF (String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAMEF + " WHERE " + COLUMN_NAMEF + "=?",new String[] {name});
        return res;
    }

}

