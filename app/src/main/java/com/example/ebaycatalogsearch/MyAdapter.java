package com.example.ebaycatalogsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<Product_details> product_details;
    Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public MyAdapter(ArrayList<Product_details> product_details, Context context) {
        this.product_details = product_details;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_results,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product_details product = product_details.get(position);
        String prod_title = product.getItemtTitle();
        String imgURL = product.getItemImg();

        String prod_ship = product.getItem_ship();
        String prod_con = product.getItem_condition();
        String prod_price = product.getItem_price();
        //Log.d("Adapter",prod_price);

        holder.product_title.setText(prod_title);
        holder.product_ship.setText("Ships for $"+prod_ship);
        holder.product_condition.setText(prod_con);
        holder.product_price.setText("$"+prod_price);
        Picasso.with(context).load(imgURL).into(holder.product_img);
    }

    @Override
    public int getItemCount() {
        return product_details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView product_title;
        private ImageView product_img;
        private TextView product_ship;
        private TextView product_condition;
        private TextView product_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_title = (TextView)itemView.findViewById(R.id.item_title);
            product_img = (ImageView) itemView.findViewById(R.id.item_img);
            product_ship = (TextView) itemView.findViewById(R.id.item_ship);
            product_condition = (TextView) itemView.findViewById(R.id.item_condition);
            product_price = (TextView) itemView.findViewById(R.id.item_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
