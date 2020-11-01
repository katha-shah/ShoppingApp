package com.example.ebaycatalogsearch;

import org.json.JSONObject;

public class Product_details {
    private String item_id;
    private String item_title;
    private String item_img;
    private String item_ship;
    private String item_condition;
    private String item_price;
    private JSONObject product_shipinfo;

    public Product_details(JSONObject product_s,String item_id, String item_title, String item_img, String item_ship, String item_con, String item_price) {
        this.product_shipinfo = product_s;
        this.item_id = item_id;
        this.item_title = item_title;
        this.item_img = item_img;
        this.item_ship = item_ship;
        this.item_condition = item_con;
        this.item_price = item_price;
        if(this.item_img.equals("https://thumbs1.ebaystatic.com/pict/04040_0.jpg"))
        {
            this.item_img = "https://www.csci571.com/hw/hw6/images/eBayLogo.png";
        }
    }
    public JSONObject getProduct_shipinfo(){return product_shipinfo;}
    public String getItem_id(){return item_id;}
    public String getItemtTitle()
    {
        return item_title;
    }
    public String getItemImg()
    {
        return item_img;
    }
    public String getItem_ship()
    {
        return item_ship;
    }
    public String getItem_condition(){
        return item_condition;
    }
    public String getItem_price()
    {
        return item_price;
    }
}
