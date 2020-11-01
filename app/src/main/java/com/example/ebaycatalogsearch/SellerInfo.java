package com.example.ebaycatalogsearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellerInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellerInfo extends Fragment {


    public SellerInfo() {
        // Required empty public constructor
    }


    public static SellerInfo newInstance(JSONObject sellerinfo, JSONObject return_policy) throws JSONException {
        SellerInfo fragment = new SellerInfo();
        Bundle bundle = new Bundle();

        Bundle args = new Bundle();
        for(int i =0; i<sellerinfo.length();i++)
        {
            Iterator<String> keys = sellerinfo.keys();
            while (keys.hasNext()) {
                String param = keys.next();
                String value = sellerinfo.get(param).toString();
                args.putString(param,value);


            }


        }

        Bundle ar2 = new Bundle();

            for(int i =0; i<return_policy.length();i++)
            {
                Iterator<String> keyr = return_policy.keys();
                while (keyr.hasNext()) {
                    String param = keyr.next();
                    String value = return_policy.get(param).toString();
                    ar2.putString(param,value);


                }

            }
            bundle.putBundle("Sellerbundle",args);
            bundle.putBundle("Return_policy",ar2);

            fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (View)inflater.inflate(R.layout.fragment_seller_info, container, false);
        if(getArguments() != null)
        {
            Set<String> iterator = getArguments().getBundle("Sellerbundle").keySet();
            Iterator<String> k = iterator.iterator();
            while(k.hasNext()) {
                String xKey = k.next();
                String xVal = getArguments().getBundle("Sellerbundle").get(xKey).toString();
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.seller_layout);
                TextView sellerinfo = new TextView(getContext());
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                p.gravity = Gravity.CENTER;
                sellerinfo.setTextSize(getResources().getDimension(R.dimen.textsize));
                sellerinfo.setPadding(0,2,1,2);

                sellerinfo.setLayoutParams(p);

                sellerinfo.setText("\u25CF "+xKey+": "+xVal);
                linearLayout.addView(sellerinfo);

            }

            Set<String> iterator2 = getArguments().getBundle("Return_policy").keySet();
            Iterator<String> k2 = iterator2.iterator();
            while(k2.hasNext()) {
                String xKey2 = k2.next();
                String xVal2 = getArguments().getBundle("Return_policy").get(xKey2).toString();
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.return_layout);
                TextView return_info = new TextView(getContext());
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                p.gravity = Gravity.CENTER;
                return_info.setTextSize(getResources().getDimension(R.dimen.textsize));
                return_info.setPadding(0,2,1,2);

                return_info.setLayoutParams(p);

                return_info.setText("\u25CF  "+xKey2+": "+xVal2);
                linearLayout.addView(return_info);

            }


        }
        return view;
    }
}
