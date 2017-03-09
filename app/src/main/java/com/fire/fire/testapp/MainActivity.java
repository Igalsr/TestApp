package com.fire.fire.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.label);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword("bramch10@gmail.com","Closed1!")
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mTextView.setText("successful");
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications");
                    reference.child("token").setValue(FirebaseInstanceId.getInstance()
                            .getToken());
                }else{
                    mTextView.setText("failed");
                }
            }
        });
    }

    public static void sendNotificationToUser(String user, final String message) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("notificationRequests");

        Map notification = new HashMap<>();
        notification.put("username", user);
        notification.put("message", message);

        ref.push().setValue(notification);
    }
}
