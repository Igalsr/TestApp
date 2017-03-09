package com.fire.fire.testapp;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by brad on 2017/03/09.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.v(TAG, "Refreshed token " + refreshToken);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications");
        reference.child("token").setValue(refreshToken);
    }
}
