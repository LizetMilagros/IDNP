package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.example.x.Registro;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DBusuario extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public static final String TABLA_USUARIOS = "usuarios";
    public static final String TABLA_REGISTRO_CAB = "registro_cab";
    public static final String TABLA_REGISTRO_DET = "registro_det";
    public static final String TABLA_PLASTICOS = "plasticos";
    public static final String TABLA_CATEGORIAS = "categorias";

    private static final String USUARIOS_QUERY = "CREATE TABLE " + TABLA_USUARIOS + "(" +
            "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT NOT NULL, " +
            "email TEXT NOT NULL, " +
            "password TEXT NOT NULL, " +
            "repassword TEXT NOT NULL)";
    private static final String CATEGORIAS_QUERY = "CREATE TABLE " + TABLA_CATEGORIAS + "(" +
            "categoria_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "categoria_name TEXT NOT NULL)";
    private static final String PLASTICO_QUERY = "CREATE TABLE " + TABLA_PLASTICOS + "(" +
            "plastico_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "plastico_name TEXT NOT NULL, " +
            "categoria_id INTEGER, " +
            "CONSTRAINT relacion1 FOREIGN KEY (categoria_id) REFERENCES " + TABLA_CATEGORIAS + " (categoria_id))";

    private static final String REGISTRO_CAB_QUERY = "CREATE TABLE " + TABLA_REGISTRO_CAB + "(" +
            "registro_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "registro_fecha TEXT NOT NULL, " +
            "user_id TEXT, " +
            "FOREIGN KEY(user_id) REFERENCES usuarios(user_id))";
    private static final String REGISTRO_DET_QUERY = "CREATE TABLE " + TABLA_REGISTRO_DET + "(" +
            "registro_id INTEGER PRIMARY KEY, " +
            "plastico_id INTEGER, " +
            "registro_cantidad INTEGER NOT NULL, " +
            "CONSTRAINT relacion2 FOREIGN KEY (registro_id) REFERENCES " + TABLA_REGISTRO_CAB + " (registro_id)," +
            "CONSTRAINT relacion3 FOREIGN KEY (plastico_id) REFERENCES " + TABLA_PLASTICOS + " (plastico_id))";

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
        MyDB.execSQL("CREATE INDEX IX_relacion1 ON "+TABLA_PLASTICOS+" (categoria_id)");
        MyDB.execSQL("CREATE INDEX IX_relacion2 ON "+TABLA_REGISTRO_DET+" (registro_id)");
        MyDB.execSQL("CREATE INDEX IX_relacion3 ON "+TABLA_REGISTRO_DET+" (plastico_id)");

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
    public Boolean registrodeplastico(String descripcion,String cantidad,String categoria){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues registrocab= new ContentValues();
        ContentValues registrodet= new ContentValues();
        ContentValues regcategoria = new ContentValues();
        ContentValues regplastico= new ContentValues();
        //registro en la cabecera
        registrocab.put("registro_fecha",new java.util.Date().toString());
        //registro de los detalles
        registrodet.put("registro_cantidad",Integer.parseInt(cantidad));
        //registro de la categoría
        regcategoria.put("categoria_name",categoria);
        //registro de la descripcion(en la tabla plastico de momento)
        regplastico.put("plastico_name",descripcion);
        //insercion de datos
        long result3 = MyDB.insert(TABLA_CATEGORIAS,null, regcategoria);
        long result4 = MyDB.insert(TABLA_PLASTICOS,null, regplastico);
        long result1 = MyDB.insert(TABLA_REGISTRO_CAB, null, registrocab);
        long result2 = MyDB.insert(TABLA_REGISTRO_DET, null, registrodet);
        if(result1==-1||result2==-1||result3==-1||result4==-1)
            return false;
        else
            return true;
    }
 /*
    public List<Registro> mostrarregistros(){
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM TABLA_REGISTRO_CAB, TABLA_REGISTRO_DET, TABLA_CATEGORIAS, TABLA_PLASTICOS",null);
        List<Registro> registros=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                registros.add(new Registro(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12),
                        cursor.getString(13),
                        cursor.getString(14)
                        ));
            }while(cursor.moveToNext());
        }
        return registros;
    }
*/

}