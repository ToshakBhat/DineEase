package com.admini.starbucks;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKID = "drinkid";
    public static final String EXTRA_TABLE = "drinkTABLE";
    String val = "demo";
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        player = MediaPlayer.create(this,R.raw.success_sound);
        //get the drink from the intent
        int drinkid = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);
        String table_name = (String)getIntent().getExtras().get(EXTRA_TABLE);
        //Drink drink = Drink.drinks[drinkid];

        SQLiteOpenHelper starbucksDatabaseHelper = new StarbucksDatabaseHelper(this);
        try{
            SQLiteDatabase db = starbucksDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query(table_name,
                    new String[]{"NAME","DESCRIPTION","IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkid)},
                    null,null,null);
            if (cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                val = nameText;
                String descriptionText = cursor.getString(1);
                int photoid = cursor.getInt(2);

                TextView name = (TextView) findViewById(R.id.name);
                name.setText(nameText);

                TextView description = (TextView) findViewById(R.id.description);
                description.setText(descriptionText);

                ImageView photo = (ImageView) findViewById(R.id.imageView);
                photo.setImageResource(photoid);

            }
            cursor.close();
            db.close();


        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }





    }
    public void OnOrder(View view){
        //Toast toast = Toast.makeText(this,val+" Ordered",Toast.LENGTH_SHORT);
        //toast.show();
        player.start();
        if(ContextCompat.checkSelfPermission(DrinkActivity.this, Manifest.permission.SEND_SMS)
        == PackageManager.PERMISSION_GRANTED){
            //When permission is granted
            sendMessage();
        }else{
            ActivityCompat.requestPermissions(DrinkActivity.this,new String[]{Manifest.permission.SEND_SMS},
                    100);
        }
    }

    private void sendMessage() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+919596676343",null,val,null,null);
        Toast toast = Toast.makeText(this,val+" ordered successfully.",Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length>0 && grantResults[0]
        == PackageManager.PERMISSION_GRANTED){
            sendMessage();
        }else{
            Toast.makeText(getApplicationContext(),"Permisson Denied!",Toast.LENGTH_SHORT).show();
        }
    }
}