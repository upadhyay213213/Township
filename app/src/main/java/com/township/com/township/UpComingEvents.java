package com.township.com.township;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * Created by nupadhay on 11/6/2016.
 */
public class UpComingEvents  extends AppCompatActivity {

    private ListView mListView;
    private String [] mEvent ={"Christmass - 25 DEC ","New Year - 1 JAN" ,"Holi - 27 MARCH"};
    private Integer [] mImage={R.drawable.chistmass,R.drawable.happy,R.drawable.holi_shop};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcomingevents);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#466b8c")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeUI();
    }

    private void initializeUI(){
        mListView = (ListView) findViewById(R.id.listUpcomingID);
        mListView.setAdapter(new UpcomingAdapter(this,mEvent,mImage));
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
