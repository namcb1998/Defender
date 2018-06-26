package com.example.namcb1998.defender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtSDT,edtPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPassword=findViewById(R.id.edtPasswordLogin);
        edtSDT=findViewById(R.id.edtSDTLogin);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnLogin){
            String sdt = edtSDT.getText().toString();
            String password = edtPassword.getText().toString();
            login(sdt,password);
        }
    }

    private void login(String sdt, final String password) {
        DatabaseReference hiepSiRef = FirebaseDatabase.getInstance().getReference().child("HiepSiAccount").child(sdt);
        hiepSiRef.child("password").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String passwordSever =dataSnapshot.getValue(String.class);
                if (passwordSever!=null){
                    if (password.equals(passwordSever)){
                        gotoMapActivity();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                }

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // need to be done
    private void gotoMapActivity() {
        Intent intent =new Intent(LoginActivity.this,MapsActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
    }

}
