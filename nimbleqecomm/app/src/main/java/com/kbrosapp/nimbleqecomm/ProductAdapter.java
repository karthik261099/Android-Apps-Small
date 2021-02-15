package com.kbrosapp.nimbleqecomm;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProductAdapter extends FirebaseRecyclerAdapter<Product,ProductAdapter.ProductViewHolder> {

    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ProductViewHolder holder, int position, @NonNull final Product model) {
        holder.name.setText(model.getName());
        holder.price.setText((model.getPrice()));
        Glide.with(holder.image.getContext()).load(model.getUrl()).into(holder.image);

        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(holder.buyButton.getContext(), model.getUrl(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(holder.buyButton.getContext(), BuyActivity.class);
                intent.putExtra("name", model.getName());
                intent.putExtra("url", model.getUrl());
                intent.putExtra("price", model.getPrice());
                holder.buyButton.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product,parent,false);
        return new ProductViewHolder(view);
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView price;
        Button buyButton;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            buyButton=itemView.findViewById(R.id.buyButton);
        }
    }
}
