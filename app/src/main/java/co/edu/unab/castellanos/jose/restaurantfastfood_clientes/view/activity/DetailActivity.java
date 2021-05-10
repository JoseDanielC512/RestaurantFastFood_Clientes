package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.R;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.entity.Product;

public class DetailActivity extends AppCompatActivity {

    private Product product;
    private TextView name, price, description;
    private ImageView picture;
    private Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        associateElements();

        product = (Product) getIntent().getSerializableExtra("product");

        name.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));
        description.setText(product.getDescription());
        Glide.with(getApplicationContext()).load(product.getUrl_picture()).into(picture);

    }

    private void associateElements() {
        name = findViewById(R.id.tv_name_detail);
        price = findViewById(R.id.tv_price_detail);
        description = findViewById(R.id.tv_description_detail);
        picture = findViewById(R.id.iv_picture_detail);
        order = findViewById(R.id.btn_order_detail);
    }
}