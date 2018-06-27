package com.example.namcb1998.defender;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.namcb1998.defender.Beans.SOSSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSOS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSOS = findViewById(R.id.btnSOSMainUserActivity);
        btnSOS.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.switchMenu){
            goToLoginActivity();
        }else {
            goToInfoActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToInfoActivity() {

    }

    private void goToLoginActivity() {
        Intent intent =new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnSOSMainUserActivity){

            //need real lat long from gps here
            btnSOS.setEnabled(false);
            sendSOSSignal(10.832133,106.634282);
        }
    }



    private void sendSOSSignal(double latitude,double longitude) {
        String token= FirebaseInstanceId.getInstance().getToken();
        DatabaseReference sosRef = FirebaseDatabase.getInstance().getReference().child("SOSSource").child(token);

        SOSSource sosSource =new SOSSource(token,false,latitude,longitude,false);

        sosRef.setValue(sosSource).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    btnSOS.setText("Đã gửi tín hiệu SOS!");
                }else {
                    btnSOS.setText("SOS!");
                    btnSOS.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Lỗi "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });



        listenForHelp(sosRef);

    }

    private void listenForHelp(DatabaseReference sosRef) {

        sosRef.child("someOneIsComing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean someOneIsComing = dataSnapshot.getValue(Boolean.class);
                if (someOneIsComing){
                    btnSOS.setText("Đang có người tới giúp");
                }else {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
