package com.township.com.township;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nupadhay on 11/6/2016.
 */
public class ServiceDetailActivity extends AppCompatActivity {

    private Button mCall, mEmail;
    private TextView serviceName, phoneNumber;
    private String mServiceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_detail);
        mServiceName = getIntent().getStringExtra("servicename");
        ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#466b8c")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeUi();
    }

    private void initializeUi() {
        mCall = (Button) findViewById(R.id.callButton);
        mEmail = (Button) findViewById(R.id.emailButton);
        serviceName = (TextView) findViewById(R.id.serviceName);
        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        serviceName.setText(mServiceName);
        phoneNumber.setText("8885233533");
        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailSend();
            }
        });

        mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent my_callIntent = new Intent(Intent.ACTION_CALL);
                    my_callIntent.setData(Uri.parse("tel:" + "8885233533"));
                    //here the word 'tel' is important for making a call...

                    startActivity(my_callIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Error in your phone call" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void emailSend(){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("vnd.android.cursor.item/email");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Enquiry Email");
        startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
