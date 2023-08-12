package com.admini.starbucks;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkCategoryActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;
    public static final String EXTRADRINK_POSITION = "drink_position";
    public String TABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        /*ArrayAdapter<Drink> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Drink.drinks
        );*/

        //TextView text = (TextView) findViewById(R.id.heading);
        int drink_position = (Integer)getIntent().getExtras().get(EXTRADRINK_POSITION);
        if(drink_position == 0){
            TextView txt = (TextView) findViewById(R.id.heading);
            txt.setText("DRINKS");
            TABLE = "DRINK";
            //IF this doesn't work change the flags
        }else{
            TextView txt = (TextView) findViewById(R.id.heading);
            txt.setText("FOOD");
            TABLE = "FOOD";
        }
        ListView listDrinks = (ListView) findViewById(R.id.list_drinks);
        SQLiteOpenHelper starbucksDatabaseHelper = new StarbucksDatabaseHelper(this);
        try{
            db = starbucksDatabaseHelper.getReadableDatabase();
            cursor = db.query(TABLE,
                    new String[]{"_id","NAME"},
                    null,null,null,null,null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);
            listDrinks.setAdapter(listAdapter);
        }catch(SQLiteException e){
            Toast toast = Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
        //
        //Create the listener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(DrinkCategoryActivity.this,DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID,(int) id);
                intent.putExtra(DrinkActivity.EXTRA_TABLE,(String) TABLE);
                startActivity(intent);
            }
        };
        listDrinks.setOnItemClickListener(itemClickListener);
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}