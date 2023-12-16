package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    private EditText mforgotpassword;
    private Button mpasswordrecoverbutton;
    private TextView mgobacktologin;


    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);



        mforgotpassword=findViewById(R.id.forgotpassword);
        mpasswordrecoverbutton=findViewById(R.id.passwordrecoverbutton);
        mgobacktologin=findViewById(R.id.gobacktoLogin);


        firebaseAuth= FirebaseAuth.getInstance();


        mgobacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forgotpassword.this, MainActivity.class);
                startActivity(intent);
            }
        });


        mpasswordrecoverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=mforgotpassword.getText().toString().trim();
                if (mail.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập email của bạn!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Gửi mật khẩu đến email

                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"Yêu cầu đổi mật khẩu đã được gửi đến email",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgotpassword.this, MainActivity.class));
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Email không tồn tại vui lòng nhập lại",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }

            }
        });

    }
}