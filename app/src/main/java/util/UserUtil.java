package util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import database.SqliteHelper;
import model.User;

/**
 * Created by pkatya on 11/6/16.
 */
public class UserUtil {

    public static boolean addStudentSuccess(User user){

       /* "user_name" VARCHAR, "email_id" VARCHAR, "password" VARCHAR,
                "full_name" VARCHAR, "phone_number" VARCHAR, "blck" VARCHAR, "flat_number" VARCHAR)*/

        if(isUserPresent(user.getUserName(),user.getEmailId())){
            return false;
        }else {
            SQLiteDatabase sqLiteDatabase = SqliteHelper.getSqliteDatabase();
            String query = "Insert into tabel_user (user_name,email_id,password,full_name,phone_number,blck,flat_number) VALUES (?,?,?,?,?,?,?)";
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(query);
            sqLiteStatement.bindString(1, user.getUserName());
            sqLiteStatement.bindString(2, user.getEmailId());
            sqLiteStatement.bindString(3, user.getPassword());
            sqLiteStatement.bindString(4, user.getFullName());
            sqLiteStatement.bindString(5, user.getPhoneNumber());
            sqLiteStatement.bindString(6, user.getBlock());
            sqLiteStatement.bindString(7, user.getFlatNumber());
            sqLiteStatement.execute();
            sqLiteStatement.close();
            return true;
        }

    }

    public static boolean isUserValid(String userName,String password){
        String query="Select * from tabel_user Where user_name=? and password=?";
        String [] projection=new String[]{userName,password};
        SQLiteDatabase db=SqliteHelper.getSqliteDatabase();
        Cursor cursor=SqliteHelper.executeSelectQuery(db,query,projection);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public static boolean isUserPresent(String userName,String email){
        String query="Select * from tabel_user Where user_name=? or email_id=?";
        String [] projection=new String[]{userName,email};
        SQLiteDatabase db=SqliteHelper.getSqliteDatabase();
        Cursor cursor=SqliteHelper.executeSelectQuery(db,query,projection);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


}
