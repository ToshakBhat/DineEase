package com.admini.starbucks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbucksDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "Starbucks";
    private static final int DB_version = 38;

    StarbucksDatabaseHelper(Context context){
        super(context,DB_Name,null,DB_version);
    }
    @Override

    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db,0,DB_version);
        /*db.execSQL("CREATE TABLE DRINK(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "DESCRIPTION TEXT)");
        insertDrink(db,"Latte","A couple of espresso shots with steamed milk");
        insertDrink(db,"Cappucino","Expresso,hot milk and a steamed milk foam");
        insertDrink(db,"Filter","Highest quality beans roasted and brewed fresh");
        //insertDrink(db,"Nimbu Paani","Cold Fresh Water having squeezed with lemons" +
          //      " with a pinch of salt");*/
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        updateMyDatabase(db,oldVersion,newVersion);

    }
    public static void insertDrink(SQLiteDatabase db,String name,String description){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME",name);
        drinkValues.put("DESCRIPTION",description);
        db.insert("DRINK",null,drinkValues);
    }
    public static void insertFood(SQLiteDatabase db,String name,String description,Integer IMAGE_RESOURCE_ID){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("DESCRIPTION",description);
        contentValues.put("IMAGE_RESOURCE_ID",IMAGE_RESOURCE_ID);
        db.insert("FOOD",null,contentValues);
    }
    private static void updateMyDatabase(SQLiteDatabase db,int oldVersion,int newVersion){
        int latte = R.drawable.latte;
        int capp = R.drawable.cappuccino;
        int filter = R.drawable.filter;
        int lemon_water = R.drawable.nimbupaani;
        if (oldVersion<1){
            db.execSQL("CREATE TABLE DRINK(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "DESCRIPTION TEXT)");
            insertDrink(db,"Latte","A couple of espresso shots with steamed milk");
            insertDrink(db,"Cappucino","Expresso,hot milk and a steamed milk foam");
            insertDrink(db,"Filter","Highest quality beans roasted and brewed fresh");
            insertDrink(db,"Nimbu Paani","Cold Fresh Water having lemons squeezed with a pinch of salt");
            db.execSQL("ALTER TABLE DRINK ADD COLUMN IMAGE_RESOURCE_ID INTEGER");
            db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+latte+" WHERE NAME ='Latte'");
            db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+capp+" WHERE NAME ='Cappucino'");
            db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+filter+" WHERE NAME ='Filter'");
            db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+lemon_water+" WHERE NAME ='Nimbu Paani'");
            String sql = "CREATE TABLE FOOD(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "DESCRIPTION TEXT," +
                    "IMAGE_RESOURCE_ID INTEGER)";
            db.execSQL(sql);
            insertFood(db,"Cheese Pizza","Dish of Italian origin consisting of a flattened " +
                    "disk of bread dough topped with some combination of olive oil, oregano, tomato," +
                    " olives, mozzarella or other cheese, and many other ingredients" +
                    "Bole toh ye khaoge to Dominos bhul jaoge!!",R.drawable.pizza);
            insertFood(db,"Dosa","A dosa is a thin pancake in South Indian cuisine" +
                    " made from a fermented batter of ground black gram (lentil) and rice.",R.drawable.dosa);
            insertFood(db,"Aloo ka Parantha","Chapattis that are stuffed with boiled," +
                    " mashed and spiced potatoes in between the layers.",R.drawable.parantha);
            insertFood(db,"Chole Bhature","It is a combination of chana masala" +
                    " and bhatura/puri, a deep-fried bread made from maida.",R.drawable.chole_bathure);
            insertFood(db,"Tandoori Chicken","A dish of roasted chicken marinated in" +
                    " yogurt and generously spiced, giving the meat its trademark red colour.",R.drawable.chicken);
            insertFood(db,"Butter Chicken","A type of curry made from chicken with" +
                    " a spiced tomato and butter (makhan) sauce.",R.drawable.butter_chicken);
            insertFood(db,"Momos","Momo are bite-size dumplings made with a spoonful" +
                    " of stuffing wrapped in dough. ",R.drawable.momos);
        }
        if(oldVersion>1){
            //insertDrink(db,"Orange Juice","Very tasty juice having taste of fresh oranges.");
            //insertDrink(db,"Nimbu Paani","Cold Fresh Water having lemons squeezed with a pinch of salt");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+lemon_water+" WHERE NAME ='Nimbu Paani'");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+R.drawable.latte+" WHERE NAME ='Latte'");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+R.drawable.cappuccino+" WHERE NAME ='Cappucino'");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+R.drawable.filter+" WHERE NAME ='Filter'");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+R.drawable.nimbupaani+" WHERE NAME ='Nimbu Paani'");
            db.execSQL("UPDATE FOOD SET IMAGE_RESOURCE_ID = "+R.drawable.momos+" WHERE NAME ='Momos'");
            //db.execSQL("UPDATE FOOD SET IMAGE_RESOURCE_ID = "+R.drawable.chicken+"WHERE NAME = 'Tandoori Chicken'");
            //db.execSQL("UPDATE FOOD SET IMAGE_RESOURCE_ID = "+R.drawable.parantha+"WHERE NAME = 'Aloo ka Parantha'");

            /*db.execSQL("UPDATE FOOD SET IMAGE_RESOURCE_ID = "+R.drawable.dosa+"WHERE NAME = 'Dosa'");
            db.execSQL("UPDATE FOOD SET IMAGE_RESOURCE_ID = "+R.drawable.parantha+"WHERE NAME = 'Aloo ka Parantha'");
            db.execSQL("UPDATE FOOD SET IMAGE_RESOURCE_ID = "+R.drawable.pizza+"WHERE NAME = 'Cheese Pizza'");
             */
            //db.execSQL("UPDATE FOOD SET IMAGE_RESOURCE_ID = "+R.drawable.chole_bathure+"WHERE NAME = 'Chole Bhature'");

            /*db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+capp+" WHERE NAME ='Cappucino'");
            db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+filter+" WHERE NAME ='Filter'");
            db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+R.drawable.butter_chicken+" WHERE NAME ='Butter Chicken'");
            db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+R.drawable.chicken+"WHERE NAME = 'Tandoori Chicken'");
            */
            /*insertFood(db,"Pizza","Dish of Italian origin consisting of a flattened " +

                    "disk of bread dough topped with some combination of olive oil, oregano, tomato," +
                    " olives, mozzarella or other cheese, and many other ingredients" +
                    "Bole toh ye khaoge to Dominos bhul jaoge!!",R.drawable.pizza);
            insertFood(db,"Dosa","A dosa is a thin pancake in South Indian cuisine" +
                    " made from a fermented batter of ground black gram (lentil) and rice.",R.drawable.dosa);
            insertFood(db,"Aloo ka Parantha","Chapattis that are stuffed with boiled," +
                    " mashed and spiced potatoes in between the layers.",R.drawable.parantha);
            insertFood(db,"Chole Bhature","It is a combination of chana masala" +
                    " and bhatura/puri, a deep-fried bread made from maida.",R.drawable.chole_bathure);
            insertFood(db,"Tandoori Chicken","A dish of roasted chicken marinated in" +
                    " yogurt and generously spiced, giving the meat its trademark red colour.",R.drawable.chicken);
            insertFood(db,"Butter Chicken","A type of curry made from chicken with" +
                    " a spiced tomato and butter (makhan) sauce.",R.drawable.butter_chicken);
            insertFood(db,"Momos","Momo are bite-size dumplings made with a spoonful" +
                    " of stuffing wrapped in dough. ",R.drawable.momos);

            */
            //db.execSQL("ALTER TABLE DRINK ADD COLUMN IMAGE_RESOURCE_ID INTEGER");
            //insertDrink(db,"Nimbu Paani","Cold Fresh Water having lemons squeezed with a pinch of salt");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+lemon_water+" WHERE NAME ='Nimbu Paani'");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+latte+" WHERE NAME ='Latte'");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+R.drawable.cappuccino+" WHERE NAME ='Cappucino'");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+R.drawable.filter+" WHERE NAME ='Filter'");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+capp+" WHERE NAME = Cappucino");
            //db.execSQL("UPDATE DRINK SET IMAGE_RESOURCE_ID = "+filter+" WHERE NAME = Filter");*/
        }
    }

}
