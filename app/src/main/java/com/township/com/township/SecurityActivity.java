package com.township.com.township;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by nupadhay on 11/6/2016.
 */
public class SecurityActivity extends AppCompatActivity {

    private TextView mServiceName , mPhoneNumber, mShiftTime,mNextShiftPerson, mNextShiftTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_layout);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#466b8c")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inflateUiView();
    }

    private void inflateUiView(){
        mServiceName = (TextView) findViewById(R.id.serviceName);
        mPhoneNumber = (TextView) findViewById(R.id.phoneNumber);
        mShiftTime = (TextView) findViewById(R.id.shiftTime);
        mNextShiftPerson = (TextView) findViewById(R.id.nextShift);
        mNextShiftTime = (TextView) findViewById(R.id.nextShiftTime);
        mServiceName.setText("Ram Kumar");
        mPhoneNumber.setText("888543897");
        mShiftTime.setText("8 AM to 6 PM");
        mNextShiftPerson.setText("Shyam Kumar");
        mNextShiftTime.setText("6PM to 6 AM");
        ;


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
