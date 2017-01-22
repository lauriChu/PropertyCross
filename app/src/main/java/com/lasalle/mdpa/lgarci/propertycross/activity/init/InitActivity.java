package com.lasalle.mdpa.lgarci.propertycross.activity.init;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lasalle.mdpa.lgarci.propertycross.R;
import com.lasalle.mdpa.lgarci.propertycross.activity.main.MainActivity;
import com.lasalle.mdpa.lgarci.propertycross.viewmodel.Property;

import static com.lasalle.mdpa.lgarci.propertycross.Arguments.TEXT_SEARCH;
import static com.lasalle.mdpa.lgarci.propertycross.Arguments.TYPE_SEARCH;

public class InitActivity extends AppCompatActivity {

    private Button searchButton, rentButton, sellButton;
    private TextInputEditText searchEditText;

    private String searchTypeSelected;

    //singletone -> patron de diseño que fuerza que la clase tenga una única instancia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        getSupportActionBar().hide();
        inflateAllProperties();
        addListenersToButtons();
    }

    private void toMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(TYPE_SEARCH, searchTypeSelected != null ? searchTypeSelected : Property.PROPERTY_TYPE_SELL);
        intent.putExtra(TEXT_SEARCH, searchEditText.getText().toString());
        startActivity(intent);
        finish();
    }

    private void addListenersToButtons(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchEditText.getText() != null && !searchEditText.getText().toString().isEmpty()){
                    toMainActivity();
                }else{
                    searchEditText.setError(getString(R.string.search_error_message));
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
    }

    private void inflateAllProperties(){
        searchButton = (Button) findViewById(R.id.button_search);
        rentButton = (Button) findViewById(R.id.button_rent);
        sellButton = (Button) findViewById(R.id.button_sell);
        searchEditText = (TextInputEditText) findViewById(R.id.editText_search);
    }
}
