package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.R;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.entity.Category;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.entity.Product;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.adapter.ListAdapter;

public class ListActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private ArrayList<Product> products;
    private ListAdapter adapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rvList = findViewById(R.id.rv_list);

        products = new ArrayList<>();
        adapter = new ListAdapter(products);


        Category category = (Category) getIntent().getSerializableExtra("category");

        /*
        adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product obj, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("product", obj);
                startActivity(intent);
            }
        });

         */


        getProducts(category.getType());

        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvList.setHasFixedSize(true);
    }

    private void getProducts(String type){

        db = FirebaseFirestore.getInstance();
        db.collection("products").whereEqualTo("type", type).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    //products.clear();
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Product product = document.toObject(Product.class);
                        products.add(product);
                    }
                    adapter.setProducts(products);
                } else {
                    Log.w("Error getting documents", task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        products.clear();
        finish();
    }
}