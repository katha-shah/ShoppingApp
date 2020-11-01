package com.example.ebaycatalogsearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShippingInfo extends Fragment {



    public ShippingInfo() {
        // Required empty public constructor
    }



    public static ShippingInfo newInstance(String param1, String param2) {
        ShippingInfo fragment = new ShippingInfo();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View)inflater.inflate(R.layout.fragment_shipping_info, container, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ship_layout);
        TextView shipinfo = new TextView(getContext());
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p.gravity = Gravity.CENTER;
        shipinfo.setTextSize(getResources().getDimension(R.dimen.textsize));
        shipinfo.setPadding(0,2,1,2);

        shipinfo.setLayoutParams(p);

        shipinfo.setText("\u25CF Handling Time: 2");

        linearLayout.addView(shipinfo);

        TextView shipinfo2 = new TextView(getContext());
        //LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //p.gravity = Gravity.CENTER;
        shipinfo2.setTextSize(getResources().getDimension(R.dimen.textsize));
        shipinfo2.setPadding(0,2,1,2);

        shipinfo2.setLayoutParams(p);

        shipinfo2.setText("\u25CF One Day Shipping Available : No");

        linearLayout.addView(shipinfo2);

        TextView shipinfo3 = new TextView(getContext());
        //LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //p.gravity = Gravity.CENTER;
        shipinfo3.setTextSize(getResources().getDimension(R.dimen.textsize));
        shipinfo3.setPadding(0,2,1,2);

        shipinfo3.setLayoutParams(p);

        shipinfo3.setText("\u25CF Shipping Type : Flat");

        linearLayout.addView(shipinfo3);

        TextView shipinfo4 = new TextView(getContext());
        //LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //p.gravity = Gravity.CENTER;
        shipinfo4.setTextSize(getResources().getDimension(R.dimen.textsize));
        shipinfo4.setPadding(0,2,1,2);

        shipinfo4.setLayoutParams(p);

        shipinfo4.setText("\u25CF Shipping From : China");

        linearLayout.addView(shipinfo4);

        TextView shipinfo5 = new TextView(getContext());
        //LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //p.gravity = Gravity.CENTER;
        shipinfo5.setTextSize(getResources().getDimension(R.dimen.textsize));
        shipinfo5.setPadding(0,2,1,2);

        shipinfo5.setLayoutParams(p);

        shipinfo5.setText("\u25CF Shipping to Locations : Worldwide");

        linearLayout.addView(shipinfo5);

        TextView shipinfo6 = new TextView(getContext());
        //LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //p.gravity = Gravity.CENTER;
        shipinfo6.setTextSize(getResources().getDimension(R.dimen.textsize));
        shipinfo6.setPadding(0,2,1,2);

        shipinfo6.setLayoutParams(p);

        shipinfo6.setText("\u25CF Expedited Shipping : No");

        linearLayout.addView(shipinfo6);

        return view;
    }
}
