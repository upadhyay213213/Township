package com.township.com.township;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String [] townArray = {"Laundry","Carpenter","Plumbing","Electrician","Bills","BroadBandTV","UpComing Events","Security"};
    private ListView mListView;
    private Integer [] mImageArray={R.drawable.ironing,R.drawable.carpentry,R.drawable.plumbing,R.drawable.electrician,R.drawable.bills,R.drawable.broadband,R.drawable.download,R.drawable.security};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.townhome);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#466b8c")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeUI();
    }

    private void initializeUI(){
        mListView = (ListView) findViewById(R.id.townListID);

        mListView.setAdapter(new TownShipAdapter(this,townArray,mImageArray));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==6){
                    Intent intent = new Intent(MainActivity.this, UpComingEvents.class);
                    intent.putExtra("servicename",townArray[position]);
                    MainActivity.this.startActivity(intent);
                }else if(position==7){
                    Intent intent = new Intent(MainActivity.this, SecurityActivity.class);
                    MainActivity.this.startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, ServiceDetailActivity.class);
                    intent.putExtra("servicename",townArray[position]);
                    MainActivity.this.startActivity(intent);
                }

            }
        });
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
