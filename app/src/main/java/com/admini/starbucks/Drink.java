package com.admini.starbucks;

public class Drink {
    private String name;
    private String description;
    private int imageResourceId;

    //drinks is an array of Drinks
    public static final Drink[] drinks = {
            new Drink("Latte","A couple of espresso shots with steamed milk"),
            new Drink("Cappucino","Expresso,hot milk and a steamed milk foam"),
            new Drink("Filter","Highest quality beans roasted and brewed fresh")
    };
    private Drink(String name,String description){
        this.name = name;
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
    public String getName(){
        return name;
    }
    public String toString(){
        return this.name;
    }


}
