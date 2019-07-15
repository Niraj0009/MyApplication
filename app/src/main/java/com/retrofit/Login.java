package com.retrofit;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    Button login_button;
    EditText login_id,login_pass;
    TextView regist;

    OnLoginFormActivityListener loginFormActivityListener;
    public interface OnLoginFormActivityListener{
        public void performRegister();
        public void performLogin(String name);
    }


    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);


        login_id=view.findViewById(R.id.login_id);
        login_pass=view.findViewById(R.id.login_pass);
        regist=view.findViewById(R.id.regist);
        login_button=view.findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getFragmentManager().beginTransaction().replace(R.id.fragment_con, new Register()).commit();
                loginFormActivityListener.performRegister();
            }
        });

        return view;
    }

    private void login(){

        String email = login_id.getText().toString();
        String pass = login_pass.getText().toString();

        Call<User> call=MainActivity.apiInterface.performUserLogin(email,pass);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body().getResponse().equals("ok")){

                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName());
                } else if(response.body().getResponse().equals("failed")) {

                    MainActivity.prefConfig.displayToast("Login Failed ! Please Try again...");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity=(Activity)context;
        loginFormActivityListener=(OnLoginFormActivityListener)activity;

    }
}
