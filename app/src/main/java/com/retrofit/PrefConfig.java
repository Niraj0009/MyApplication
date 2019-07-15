package com.retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConfig {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public PrefConfig(Context context){
        this.context = context;
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);

    }

    public void writeLoginStatus(boolean status){
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    public void writeName(String name){
        editor.putString(context.getString(R.string.pref_user_name),name);
        editor.commit();
    }

    public String readName(){

        return sharedPreferences.getString(context.getString(R.string.pref_user_name),"user");
    }
    
    public void displayToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
