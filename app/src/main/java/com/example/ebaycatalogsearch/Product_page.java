package com.example.ebaycatalogsearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Product_page extends Fragment {
    ArrayList<String> pictureURL;
    public Product_page() {

    }

    public Product_page newInstance( ArrayList<String> pictureURL, String prod_title, String prod_ship, String prod_price)
    {
        Product_page fragment = new Product_page();
        Bundle args = new Bundle();
        args.putStringArrayList("pictureURL",pictureURL);
        args.putString("title", prod_title);
        args.putString("ship", prod_ship);
        args.putString("price",prod_price);

        fragment.setArguments(args);
        return fragment;
    }


//    public static Product_page newInstance(String prod_title, String prod_ship, String prod_price) {
//        Product_page fragment = new Product_page();
//        Bundle args = new Bundle();
//
//        args.putString("title", prod_title);
//        args.putString("ship", prod_ship);
//        args.putString("price",prod_price);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> arr;
        View view = (View) inflater.inflate(R.layout.fragment_product_page, container, false);
        TextView prod_title = (TextView)view.findViewById(R.id.frag_prod_title);
        TextView prod_ship = (TextView)view.findViewById(R.id.frag_prod_ship);
        TextView prod_price = (TextView)view.findViewById(R.id.frag_prod_price);

        if (getArguments() != null) {
            arr = getArguments().getStringArrayList("pictureURL");
            String itemship = getArguments().getString("ship");
            String itemprice = getArguments().getString("price");
            String itemtitle = getArguments().getString("title");

            for(int j =0; j< arr.size(); j++)
            {

                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.product_layout);
                ImageView img = new ImageView(getContext());
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                p.gravity = Gravity.CENTER;

                p.setMargins(0, 0, 0, 0);
                img.setLayoutParams(p);

                img.setScaleType(ImageView.ScaleType.FIT_XY);
                img.getLayoutParams().width = 1300;
                img.getLayoutParams().height = 900;
                Picasso.with(getContext()).load(arr.get(j)).into(img);

                linearLayout.addView(img);
            }


            prod_title.setText(itemtitle);
            prod_price.setText("$"+itemprice);
            prod_ship.setText("Ships for $"+itemship);

            TextView textView = (TextView) view.findViewById(R.id.product_feat_icon);
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.info_icon_foreground, 0, 0, 0);



            }
            //String picturone = arr.get(0);
            //product_title.setText(picturone);
        return view;
        }

    }

//
//        ImageView product_image1 = (ImageView) view.findViewById(R.id.picture_one);
//        if(getArguments()!=null)
//
//            String itemship = getArguments().getString("ship");
//            String itemprice = getArguments().getString("price");



//            product_price.setText("$"+itemprice);
//            product_ship.setText("Ships for $"+itemship);






