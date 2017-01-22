package com.lasalle.mdpa.lgarci.propertycross.activity.init;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.lasalle.mdpa.lgarci.propertycross.activity.init.manager.InitActivityManager;

import static com.lasalle.mdpa.lgarci.propertycross.googleapi.LocationManager.REQUEST_FINE_LOCATION;

public class InitActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private InitActivityManager initActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityManager = new InitActivityManager(this);
        initActivityManager.initViewLayout();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_FINE_LOCATION && permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[0] == 0){
            initActivityManager.doGoogleConnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        initActivityManager.onGoogleConnected();
    }


    @Override
    public void onConnectionSuspended(int i) {
        initActivityManager.doGoogleConnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // TODO: Dialogo fallo en connexión ubicación
    }

    /*@Override
    protected void onStart() {
        initActivityManager.doGoogleConnect();
        super.onStart();
    }*/

    @Override
    protected void onStop() {
        initActivityManager.doGoogleDisconnect();
        super.onStop();
    }
}
