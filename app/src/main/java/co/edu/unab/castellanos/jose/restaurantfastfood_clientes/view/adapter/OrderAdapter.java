package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.R;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.entity.Order_products;

public class OrderAdapter extends RecyclerView.Adapter {


    private ArrayList<Order_products> products;
    private OnItemClickListener onItemClickListener;

    public OrderAdapter(ArrayList<Order_products> products) {

        this.products = products;
        this.onItemClickListener = null;
    }


    public void setProducts(ArrayList<Order_products> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivPicture;
        private TextView tvName, tvAmount, tvPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_pic_order);
            tvName = itemView.findViewById(R.id.tv_name_product);
            tvAmount = itemView.findViewById(R.id.tv_amount_product);
            tvPrice = itemView.findViewById(R.id.tv_totalprice_product);
        }

        public void enlazar(Order_products products){
            tvName.setText(products.getProduct().getName());
            tvAmount.setText(Integer.toString(products.getAmount()));
            tvPrice.setText(Integer.toString(products.getPrice()));
            Glide.with(ivPicture.getContext()).load(products.getProduct().getUrl_picture()).into(ivPicture);

            if (onItemClickListener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(products, getAdapterPosition());
                    }
                });
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Order_products product = products.get(position);
        ProductViewHolder miHolder = (ProductViewHolder) holder;
        miHolder.enlazar(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface OnItemClickListener{

        void onItemClick(Order_products obj, int position);
    }
}
