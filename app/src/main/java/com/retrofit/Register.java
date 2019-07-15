package com.retrofit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {
    EditText Customer_id,register_user_name,Register_Email,Register_Phone,Register_Password;
    Button Registration_button;


    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        Customer_id= view.findViewById(R.id.Customer_id);
        register_user_name=view.findViewById(R.id.register_user_name);
        Register_Email=view.findViewById(R.id.Register_Email);
        Register_Phone=view.findViewById(R.id.Register_Phone);
        Register_Password=view.findViewById(R.id.Register_Password);
        Registration_button=view.findViewById(R.id.Registration_button);
        //register_login_text=view.findViewById(R.id.register_login_text);

        Registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


        return view;
    }
    private void register(){
        String sponsor_id=Customer_id.getText().toString();
        String name=register_user_name.getText().toString();
        String email_id=Register_Email.getText().toString();
        String phone=Register_Phone.getText().toString();
        String password=Register_Password.getText().toString();


        Call<User> call=MainActivity.apiInterface.performRegistration(sponsor_id,name,email_id,phone,password);

       call.enqueue(new Callback<User>() {
           @Override
           public void onResponse(Call<User> call, Response<User> response) {
               if(response.body().getResponse().equals("ok")){
                   MainActivity.prefConfig.displayToast("Registration success....");
               }
               else if (response.body().getResponse().equals("exist")){
                   MainActivity.prefConfig.displayToast("User already exist ");
               }
               else if (response.body().getResponse().equals("error")){
                   MainActivity.prefConfig.displayToast("Something went wrong ");
               }
           }

           @Override
           public void onFailure(Call<User> call, Throwable t) {
               Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

           }
       });

    }

}
