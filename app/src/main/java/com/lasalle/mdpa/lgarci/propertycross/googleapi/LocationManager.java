package com.lasalle.mdpa.lgarci.propertycross.googleapi;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.lasalle.mdpa.lgarci.propertycross.activity.init.InitActivity;

/**
 * Created by FurruPi on 22/1/17.
 */

public abstract class LocationManager {


    /**
     * Constant for request fine location
     */
    public static final Integer REQUEST_FINE_LOCATION = 123;
    public static final String TAG_LOCATION_MANAGER = "location-update";
    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    /**
     * Do Google Connection
     */
    public void doGoogleConnect(GoogleApiClient googleApiClient){
        googleApiClient.connect();
    }

    /**
     * Disconnect GoogleConnection
     */
    public void doGoogleDisconnect(GoogleApiClient googleApiClient){
        googleApiClient.disconnect();
    }

    /**
     * Get default Google Connection
     * @param activity : should implements ConnectionCallback and OnConnectionFailedListener
     * @return google connection
     */
    public GoogleApiClient getDefaultGoogleConnection(Activity activity){
        return new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) activity)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) activity)
                .addApi(LocationServices.API)
                .build();
    }

    public static LocationRequest getDefaultLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    /**
     * Start Updating Location
     * @param activity - need implements LocationListener
     * @param mGoogleApiClient
     * @param mLocationRequest
     */
    public static void startLocationUpdates(InitActivity activity, GoogleApiClient mGoogleApiClient, LocationRequest mLocationRequest) {
        // The final argument to {@code requestLocationUpdates()} is a LocationListener
        // (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_FINE_LOCATION);
            return;
        }
        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, (LocationListener) activity);
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    /**
     * Stop Updating Location
     * @param activity - need implements LocationListener
     * @param mGoogleApiClient
     */
    public static void stopLocationUpdates(Activity activity, GoogleApiClient mGoogleApiClient) {
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.

        // The final argument to {@code requestLocationUpdates()} is a LocationListener
        // (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
        try {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (LocationListener) activity);
        }catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public abstract void onGoogleConnected();

    public abstract void doGoogleConnect();

    public abstract void doGoogleDisconnect();
}
