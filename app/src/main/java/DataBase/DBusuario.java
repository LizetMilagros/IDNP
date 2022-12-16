package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBusuario extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public static final String TABLA_USUARIOS = "usuarios";
    public static final String TABLA_REGISTRO_CAB = "registro_cab";
    public static final String TABLA_REGISTRO_DET = "registro_det";
    public static final String TABLA_PLASTICOS = "plasticos";
    public static final String TABLA_CATEGORIAS = "categorias";

    private static final String USUARIOS_QUERY = "CREATE TABLE "+TABLA_USUARIOS+"(user_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL, repassword TEXT NOT NULL)";
    private  static final String CATEGORIAS_QUERY = "CREATE TABLE "+TABLA_CATEGORIAS+"(categoria_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, categoria_name TEXT NOT NULL)";
    private  static final String PLASTICO_QUERY = "CREATE TABLE "+TABLA_PLASTICOS+"(plastico_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, plastico_name TEXT NOT NULL, categoria_id INTEGER, FOREIGN KEY(\"categoria_id\") REFERENCES \"categorias\"(\"categoria_id\"))";
    private static final String REGISTRO_CAB_QUERY = "CREATE TABLE "+TABLA_REGISTRO_CAB+"(registro_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, registro_fecha TEXT NOT NULL, user_id TEXT, FOREIGN KEY(\"user_id\") REFERENCES \"usuarios\"(\"user_id\"))";
    private static final String REGISTRO_DET_QUERY = "CREATE TABLE "+TABLA_REGISTRO_DET+"(registro_id INTEGER NOT NULL PRIMARY KEY, plastico_id INTEGER NOT NULL, registro_cantidad INTEGER NOT NULL, FOREIGN KEY(\"registro_id\") REFERENCES \"registro_cab\"(\"registro_id\"), FOREIGN KEY(\"plastico_id\") REFERENCES \"plasticos\"(\"plastico_id\"))";

    public DBusuario(Context context) {
        super(context, "Login.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL(USUARIOS_QUERY);
        MyDB.execSQL(CATEGORIAS_QUERY);
        MyDB.execSQL(PLASTICO_QUERY);
        MyDB.execSQL(REGISTRO_CAB_QUERY);
        MyDB.execSQL(REGISTRO_DET_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS "+ TABLA_USUARIOS);
        MyDB.execSQL("DROP TABLE IF EXISTS "+ TABLA_REGISTRO_CAB);
        MyDB.execSQL("DROP TABLE IF EXISTS "+ TABLA_REGISTRO_DET);
        MyDB.execSQL("DROP TABLE IF EXISTS "+ TABLA_PLASTICOS);
        MyDB.execSQL("DROP TABLE IF EXISTS "+ TABLA_CATEGORIAS);
        onCreate(MyDB);
    }

    public Boolean insertData(String username, String correo, String password, String repassword){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", correo);
        contentValues.put("password", password);
        contentValues.put("repassword", repassword);
        long result = MyDB.insert(TABLA_USUARIOS, null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+ TABLA_USUARIOS +" where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkemailpassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+ TABLA_USUARIOS +" where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}