package com.example.ebaycatalogsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView key_empty;
    TextView price_err;
    CheckBox newitem;
    CheckBox useditem;
    CheckBox unspecified;
    String sort_criteria;
    Button search_btn;
    Button clear_btn;


    String itemname;
    public static int item_price_from;
    public static int item_price_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final EditText key_words = findViewById(R.id.item_name);
        final EditText pricefrom = findViewById(R.id.pricefrom);
        final EditText priceto = findViewById(R.id.priceto);

        newitem = (CheckBox)findViewById(R.id.newitem);
        useditem = (CheckBox)findViewById(R.id.useditem);
        unspecified = (CheckBox)findViewById(R.id.unspecified_item);

        final Spinner sortby = (Spinner) findViewById(R.id.sortby);

        search_btn = (Button) findViewById(R.id.search_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sort_by, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortby.setAdapter(adapter);
        sortby.setSelection(0);
        sortby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sort_criteria = parent.getItemAtPosition(position).toString();
                if(sort_criteria.equals("Price: Highest first"))
                {
                    sort_criteria = "CurrentPriceHighest";
                }
                else if(sort_criteria.equals("Price + Shipping: Highest first"))
                {
                    sort_criteria = "PricePlusShippingHighest";
                }
                else if(sort_criteria.equals("Price + Shipping: Lowest first"))
                {
                    sort_criteria = "PricePlusShippingLowest";
                }
                else{
                    sort_criteria = "BestMatch";
                }
                //Toast.makeText(getApplicationContext(),sort_criteria,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item_name = key_words.getText().toString();
                String price_from = pricefrom.getText().toString();
                String price_to = priceto.getText().toString();
                if (price_from.isEmpty()) {
                    item_price_from = 0;
                } else {
                    item_price_from = Integer.parseInt(price_from);
                }
                if (price_to.isEmpty()) {
                    item_price_to = 0;
                } else {
                    item_price_to = Integer.parseInt(price_to);
                }

                if (item_name.isEmpty()) {
                    key_empty = (TextView) findViewById(R.id.keyword_err);
                    key_empty.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please fix all fields with errors",Toast.LENGTH_LONG).show();
                }
                else if(item_price_to < item_price_from || item_price_to<0 ||item_price_from<0)
                {
                    price_err = (TextView) findViewById(R.id.price_err);
                    price_err.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please fix all fields with errors",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, SearchResults.class);
                    intent.putExtra("itemname", item_name);
                    intent.putExtra("pricefrom", item_price_from);
                    intent.putExtra("priceto", item_price_to);
                    intent.putExtra("new_item", newitem.isChecked());
                    intent.putExtra("used_item", useditem.isChecked());
                    intent.putExtra("unspec_item", unspecified.isChecked());
                    intent.putExtra("sort_by", sort_criteria);

                    startActivity(intent);
                }
            }
        });
    }
}
