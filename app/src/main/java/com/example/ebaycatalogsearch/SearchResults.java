package com.example.ebaycatalogsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    ProgressBar progressBar;
    RecyclerView myrecycler;
    TextView searching;
    TextView totalEntries;

    private MyAdapter myAdapter;
    private ArrayList<Product_details> productDetails;
    private RequestQueue mRequestQueue;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);


        Intent intent = getIntent();
        final String itemname = intent.getStringExtra("itemname");
        int pricefrom = intent.getExtras().getInt("pricefrom");
        int priceto = intent.getExtras().getInt("priceto");

        Boolean newitem = intent.getExtras().getBoolean("new_item");
        Boolean useditem = intent.getExtras().getBoolean("used_item");
        Boolean unspec_item = intent.getExtras().getBoolean("unspec_item");

        String sort_by = intent.getStringExtra("sort_by");

        String params = "?itemname="+itemname+"&pricefrom="+pricefrom+"&priceto="+priceto+"&newitem="+newitem+"&useditem="+useditem+"&unspec_item="+unspec_item+"&sorting="+sort_by;
        String endpoint = "https://ebay-hw.wl.r.appspot.com/item_search/";
        String URL = endpoint + params;

        TextView totalEntries = (TextView) findViewById(R.id.totalEntries);

        progressBar = findViewById(R.id.progress_bar);
        searching = findViewById(R.id.show_progress);
        progressBar.setVisibility(View.VISIBLE);

        myrecycler = (RecyclerView) findViewById(R.id.recycler_view);
        myrecycler.setHasFixedSize(true);

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        myrecycler.addItemDecoration(new DividerItemDecoration(myrecycler.getContext(), DividerItemDecoration.VERTICAL));
        myrecycler.addItemDecoration(new DividerItemDecoration(myrecycler.getContext(), DividerItemDecoration.HORIZONTAL));
        myrecycler.setLayoutManager(manager);

        productDetails = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            TextView noresults = (TextView) findViewById(R.id.noresults);
                            TextView total_Entries = (TextView) findViewById(R.id.totalEntries);
                            if(response.length() == 1)
                            {
                                total_Entries.setVisibility(View.GONE);
                                noresults.setVisibility(View.VISIBLE);
                                noresults.setText("No records");
                                Toast.makeText(getApplicationContext(),"No Records",Toast.LENGTH_SHORT).show();

                            }


                            int totalEntries = response.length();

                            if (totalEntries == 0)
                            {
                                noresults.setText("No Records");
                                Toast.makeText(getApplicationContext(),"No Records",Toast.LENGTH_SHORT).show();
                            }
                            int size;
                            if (totalEntries >= 50)
                            {
                                size = 50;
                            }
                            else{
                                size = totalEntries;
                            }
                            total_Entries.setText("Showing "+size+" results for "+itemname);
                            for (int i = 0; i < size; i++) {

                                JSONObject findItems = response.getJSONObject(i);
                                JSONArray prod_title = findItems.getJSONArray("title");
                                JSONArray product_id = findItems.getJSONArray("itemId");
                                JSONArray prod_img = findItems.getJSONArray("galleryURL");
                                JSONArray prod_ship = findItems.getJSONArray("shippingInfo");
                                JSONArray prod_con = findItems.getJSONArray("condition");
                                JSONArray prod_price = findItems.getJSONArray("sellingStatus");

                                String productTitle = prod_title.get(0).toString();
                                String product_img = prod_img.get(0).toString();
                                String productid = product_id.get(0).toString();

                                JSONObject product_sh = prod_ship.getJSONObject(0);
                                JSONArray product_shi = product_sh.getJSONArray("shippingServiceCost");
                                JSONObject product_s = product_shi.getJSONObject(0);
                                String product_ship_cost = product_s.getString("__value__");

                                JSONObject product_co = prod_con.getJSONObject(0);
                                JSONArray product_c = product_co.getJSONArray("conditionDisplayName");
                                String product_condition = product_c.get(0).toString();

                                JSONObject product_pr = prod_price.getJSONObject(0);
                                JSONArray product_p = product_pr.getJSONArray("convertedCurrentPrice");
                                JSONObject product_pric = product_p.getJSONObject(0);
                                String product_price = product_pric.getString("__value__");
                                Log.d("Ahiya",product_price);
                                productDetails.add(new Product_details(product_sh,productid,productTitle,product_img,product_ship_cost,product_condition,product_price));
                            }
                            myAdapter = new MyAdapter(productDetails,SearchResults.this);
                            myrecycler.setAdapter(myAdapter);
                            myAdapter.setOnItemClickListener(SearchResults.this);

                        } catch (JSONException e) {
                            //TextView noresults = (TextView) findViewById(R.id.noresults);
                            //noresults.setText("No records");

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
        //progressBar.setVisibility(View.INVISIBLE);
        //totalEntries.setText(totalEntries.toString());
        mRequestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
            @Override
            public void onRequestFinished(Request<String> request) {
                progressBar.setVisibility(View.INVISIBLE);
                searching.setVisibility(View.GONE);
            }
        });

    }



    @Override
    public void onItemClick(int position) {
        Intent detailed_page = new Intent(this, Details_page.class);
        Product_details clicked_product = productDetails.get(position);



        detailed_page.putExtra("productship", (clicked_product.getProduct_shipinfo()).toString());
        detailed_page.putExtra("productid",clicked_product.getItem_id());
        detailed_page.putExtra("producttitle",clicked_product.getItemtTitle());
        detailed_page.putExtra("productship",clicked_product.getItem_ship());
        detailed_page.putExtra("productprice",clicked_product.getItem_price());

        startActivity(detailed_page);


    }
}
