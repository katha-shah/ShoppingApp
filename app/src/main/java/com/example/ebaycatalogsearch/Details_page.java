package com.example.ebaycatalogsearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Details_page extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private Product_page product_fragment;
    private SellerInfo seller_info_fragment;
    private ShippingInfo shipping_info_fragment;

    private RequestQueue requestQueue;
    public ArrayList<String> product_details ;
    public Map<String,String> sellerobj = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        viewPager = findViewById(R.id.view_pager);

        Intent intent = getIntent();
        final String productid = intent.getStringExtra("productid");
        final  String product_title = intent.getStringExtra("producttitle");
        final String productship = intent.getStringExtra("productship");
        final String productprice = intent.getStringExtra("productprice");
        final String prod_ship = intent.getStringExtra("productship");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tab_layout);

        String url = "https://ebay-hw.wl.r.appspot.com/product_details/?productid="+productid;

        requestQueue = Volley.newRequestQueue(this);
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        product_details = new ArrayList<>();
       final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
           @Override
            public void onResponse(JSONObject response) {

               try {
                    JSONArray res = response.getJSONArray("PictureURL");
                    JSONObject seller = response.getJSONObject("Seller");
                    JSONObject return_policies = response.getJSONObject("ReturnPolicy");
                    //JSONObject item_specs = response.getJSONObject("ItemSpecifics");
                    //JSONArray item_spec = item_specs.getJSONArray("NameValueList");
                    //Log.d("raj1",item_spec.toString());
                    for(int i=0; i< res.length(); i++)
                    {
                        String picture_one = res.get(i).toString();
                        product_details.add(picture_one);
                    }

                    //Log.d("raj",return_policies.toString());
                    Product_page pge = new Product_page().newInstance(product_details,product_title,productship,productprice);
                    SellerInfo sellerInfo = new SellerInfo().newInstance(seller,return_policies);
                   ShippingInfo shipping_info_fragment = new ShippingInfo();

                   viewPagerAdapter.addFragment(pge,"PRODUCT");
                   viewPagerAdapter.addFragment(sellerInfo,"SELLER INFO");
                   viewPagerAdapter.addFragment(shipping_info_fragment,"SHIPPING");
                   viewPager.setAdapter(viewPagerAdapter);
                   tabLayout.setupWithViewPager(viewPager);
                   tabLayout.getTabAt(2).setIcon(R.drawable.truck_foreground);
                   tabLayout.getTabAt(1).setIcon(R.drawable.info_icon_foreground);
                   tabLayout.getTabAt(0).setIcon(R.drawable.seller_icon_1);

               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }

       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
           }
       });

        requestQueue.add(request);





       // tabLayout.getTabAt(2).setIcon(R.drawable.product_info);


    }
    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String > fragmenttitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmenttitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmenttitle.get(position);
        }
    }
}
