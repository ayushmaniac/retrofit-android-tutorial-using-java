package com.gradlic.student.project.retrofitdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleUserActivity extends AppCompatActivity {

    TextView firstNameTv, lastNameTv, emailTv, passwordTv;

    EditText userIdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user);

        Button button = findViewById(R.id.find_user_button);
        firstNameTv = findViewById(R.id.show_firstname);
        lastNameTv = findViewById(R.id.show_lastname);
        emailTv = findViewById(R.id.show_email);
        passwordTv = findViewById(R.id.show_password);

        userIdEt = findViewById(R.id.user_id);

        Button deleteButton = findViewById(R.id.delete_user_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitService.getInstance().getJSONApi().deleteUser(Integer.parseInt(userIdEt.getText().toString())).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(SingleUserActivity.this,
                                "User deleted with id: "+userIdEt.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

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
                        User user = response.body();

                        if (response.isSuccessful()){
                            firstNameTv.setText(user.getFirstName());
                            lastNameTv.setText(user.getLastName());
                            emailTv.setText(user.getEmail());
                            passwordTv.setText(user.getPassword());

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
