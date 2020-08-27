package com.example.boxingtimer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public String roundsNum;
    public String roundLen;
    public String restLen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // GETS THE SPINNERS SET UP FOR JAVA AND DOES THE ITEMSELECTED
        Spinner roundsSpinner = findViewById(R.id.roundsSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rounds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roundsSpinner.setAdapter(adapter);
        roundsSpinner.setOnItemSelectedListener(this);

        Spinner timeSpinner = findViewById(R.id.timeSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter2);
        timeSpinner.setOnItemSelectedListener(this);

        Spinner restSpinner = findViewById(R.id.restSpinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.rest, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        restSpinner.setAdapter(adapter3);
        restSpinner.setOnItemSelectedListener(this);
    }
    // THIS CREATES A NEW PAGE AND PASSES THE VARIABLES OVER TO THE OTHER ACTIVITY, CORRESPONDS TO THE BRINGSOVER() METHOD
    //A BUTTON DOES THIS AND IN THE ATTRIBUTES FOR IT THE ONCLICK IS CHANGES TO NEXTPAGE
    public void NextPage(View v) {
        Intent myIntent = new Intent(this, TimerPage.class);
        myIntent.putExtra("key1", roundsNum);
        myIntent.putExtra("key2", roundLen);
        myIntent.putExtra("key3", restLen);
        startActivity(myIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // CHECKS THE PARENT ie WHICH SPINNER WAS SELECTED AND ASSIGNS THE VARIABLE TO THE VALUE
        switch (parent.getId()){
            case R.id.roundsSpinner:
                roundsNum = parent.getItemAtPosition(position).toString();
                break;

            case R.id.timeSpinner:
                roundLen = parent.getItemAtPosition(position).toString();
                break;

            case R.id.restSpinner:
                restLen = parent.getItemAtPosition(position).toString();
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}