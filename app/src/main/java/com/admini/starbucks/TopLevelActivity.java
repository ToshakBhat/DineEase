package com.admini.starbucks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.Driver;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_category_activity);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this,DrinkCategoryActivity.class);
                    intent.putExtra(DrinkCategoryActivity.EXTRADRINK_POSITION,0);
                    startActivity(intent);
                }else if(position == 1){
                    Intent intent = new Intent(TopLevelActivity.this,DrinkCategoryActivity.class);
                    intent.putExtra(DrinkCategoryActivity.EXTRADRINK_POSITION,1);
                    startActivity(intent);
                }
            }
        };
        //Add the listener to the listview
        ListView listview = (ListView) findViewById(R.id.list_options);
        listview.setOnItemClickListener(itemClickListener);
    }
}