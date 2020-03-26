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

public class AddUserActivity extends AppCompatActivity {

    EditText firstNameEt, lastNameEt, passwordEt, emailEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        firstNameEt = findViewById(R.id.first_name);
        lastNameEt = findViewById(R.id.last_name);
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);

        Button button = findViewById(R.id.submit_user);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(null,
                        firstNameEt.getText().toString(),
                        lastNameEt.getText().toString(),
                        emailEt.getText().toString(),
                        passwordEt.getText().toString());

                RetrofitService.getInstance().getJSONApi().addUser(user).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(AddUserActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddUserActivity.this, "Not Success", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(AddUserActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
