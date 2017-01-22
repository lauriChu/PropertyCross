package com.lasalle.mdpa.lgarci.propertycross.activity.init.manager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.lasalle.mdpa.lgarci.propertycross.R;
import com.lasalle.mdpa.lgarci.propertycross.activity.init.InitActivity;
import com.lasalle.mdpa.lgarci.propertycross.activity.main.MainActivity;
import com.lasalle.mdpa.lgarci.propertycross.googleapi.LocationManager;
import com.lasalle.mdpa.lgarci.propertycross.viewmodel.Property;

import static com.lasalle.mdpa.lgarci.propertycross.Arguments.TEXT_SEARCH;
import static com.lasalle.mdpa.lgarci.propertycross.Arguments.TYPE_SEARCH;

/**
 * Created by FurruPi on 22/1/17.
 */

public class InitActivityManager extends LocationManager{
    private InitActivity mActivity; //InitActivity

    /**
     * Layout Views
     */
    private Button searchButton, rentButton, sellButton, locationButton;
    private TextInputEditText searchEditText;
    private String searchTypeSelected;

    /**
     * GoogleApi - Get current location
     */
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    /**
     * Public constructor of manager
     * @param initActivity
     */
    public InitActivityManager(InitActivity initActivity){
        this.mActivity = initActivity;
        mGoogleApiClient = getDefaultGoogleConnection(mActivity);
    }

    /**
     * Initialize Layout
     */
    public void initViewLayout(){
        mActivity.setContentView(R.layout.activity_init);
        mActivity.getSupportActionBar().hide();
        inflateAllViews();
        addListeners();
    }

    /**
     * Inflate All Views of the Layout
     */
    private void inflateAllViews() {
        searchButton = (Button) mActivity.findViewById(R.id.button_search);
        rentButton = (Button) mActivity.findViewById(R.id.button_rent);
        sellButton = (Button) mActivity.findViewById(R.id.button_sell);
        locationButton = (Button) mActivity.findViewById(R.id.button_location);
        searchEditText = (TextInputEditText) mActivity.findViewById(R.id.editText_search);
    }

    /**
     * Add Listeners for buttons
     */
    private void addListeners() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchEditText.getText() != null && !searchEditText.getText().toString().isEmpty()) {
                    toMainActivity();
                } else {
                    searchEditText.setError(mActivity.getString(R.string.search_error_message));
                }
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTypeSelected = Property.PROPERTY_TYPE_SELL;
            }
        });

        rentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTypeSelected = Property.PROPERTY_TYPE_RENT;
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLastLocation != null) {
                   setTextOfLocation();
                }else{
                    mGoogleApiClient.disconnect();
                    mGoogleApiClient.connect();
                }
            }
        });
    }

    /**
     * Add Text Of Current Location
     */
    private void setTextOfLocation(){
        searchEditText.setText(String.valueOf(mLastLocation.getLatitude()) + " " +
                String.valueOf(mLastLocation.getLongitude()));
        // TODO: APi Google for extract the name
    }

    /**
     * On GoogleConnection established method - obtains current location
     */
    public void onGoogleConnected(){
        if (ActivityCompat.checkSelfPermission(mActivity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_FINE_LOCATION);

            mGoogleApiClient.disconnect();
            return;
        }

        // If the initial location was never previously requested, we use
        // FusedLocationApi.getLastLocation() to get it. If it was previously requested, we store
        // its value in the Bundle and check for it in onCreate(). We
        // do not request it again unless the user specifically requests location updates by pressing
        // the Start Updates button.
        //
        // Because we cache the value of the initial location in the Bundle, it means that if the
        // user launches the activity,
        // moves to a new location, and then changes the device orientation, the original location
        // is displayed as the activity is re-created.

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation != null) {
            setTextOfLocation();
        }
    }

    @Override
    public void doGoogleConnect() {
        mGoogleApiClient.connect();
    }

    @Override
    public void doGoogleDisconnect() {
        mGoogleApiClient.disconnect();
    }

    /**
     * Navigates To MainActivity
     */
    private void toMainActivity() {
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.putExtra(TYPE_SEARCH, searchTypeSelected != null ? searchTypeSelected : Property.PROPERTY_TYPE_SELL);
        intent.putExtra(TEXT_SEARCH, searchEditText.getText().toString());
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
