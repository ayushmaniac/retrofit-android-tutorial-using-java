package com.gradlic.student.project.retrofitdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserActivity extends AppCompatActivity {

    EditText firstNameEt, lastNameEt, passwordEt, emailEt;
    EditText userIdEt;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);


        firstNameEt = findViewById(R.id.first_name);
        lastNameEt = findViewById(R.id.last_name);
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);

        Button button = findViewById(R.id.submit_user);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                RetrofitService.getInstance().getJSONApi().updateUser(user, user.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(UpdateUserActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(UpdateUserActivity.this, "Not Success", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(UpdateUserActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitService.getInstance().getJSONApi().getUser(Integer.parseInt(userIdEt.getText().toString())).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        user = response.body();

                        if (response.isSuccessful()){
                            firstNameEt.setText(user.getFirstName());
                            lastNameEt.setText(user.getLastName());
                            emailEt.setText(user.getEmail());
                            passwordEt.setText(user.getPassword());

                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });



    }
}
