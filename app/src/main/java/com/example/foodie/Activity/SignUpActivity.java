package com.example.foodie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodie.R;
import com.example.foodie.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_sign_up);

        setVariable();
    }

    private void setVariable() {
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String email = binding.emailSign.getText().toString();
                    String password = binding.passwordSign.getText().toString();

                    if (password.length() < 6) {
                        Toast.makeText(SignUpActivity.this, "Your password must be 6 characters", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.i(TAG, "onComplete: User creation successful");
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Log.e(TAG, "onComplete: User creation failed", task.getException());
                                        Toast.makeText(SignUpActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } catch (Exception e) {
                    Log.e(TAG, "Exception in onClick", e);
                    // Handle the exception or log it as needed
                    Toast.makeText(SignUpActivity.this, "An unexpected error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
