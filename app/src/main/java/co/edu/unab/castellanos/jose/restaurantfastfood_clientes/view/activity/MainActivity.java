package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.R;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.Category;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.adapter.CategoryAdapter;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Category> categories;
    private RecyclerView rvCategories;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.inicio));
        fillCategories();
        rvCategories = findViewById(R.id.rv_categories);
        adapter = new CategoryAdapter(categories);

        adapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category obj, int position) {
                /*
                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("datos", obj);
                startActivityForResult(intent, CODIGO_DETALLE_PRODUCTO);
                 */
            }
        });

        rvCategories.setAdapter(adapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvCategories.setHasFixedSize(true);
    }

    private void fillCategories(){
        categories = new ArrayList<>();
        if (categories.isEmpty()){
            categories.add(new Category("Hamburguesas", "Lorem Ipsum", "https://d3jv0cqma81l17.cloudfront.net/articulos/1534/imagen_destacadas/original/5038_burger-master-bucaramanga-2019.jpg?1573812560"));
            categories.add(new Category("Perros", "Lorem Ipsum", "https://juegoscocinarpasteleria.org/wp-content/uploads/2020/04/C%C3%B3mo-hervir-perros-calientes.jpg"));
            categories.add(new Category("Salchipapas", "Lorem Ipsum", "https://abrecht-group.com/wp-content/uploads/2017/03/5a134daf63fca.jpeg"));
            categories.add(new Category("Choripapas", "Lorem Ipsum", "https://media.domicompras.com/aliados/970/productos/4.png"));
            categories.add(new Category("Otros", "Lorem Ipsum", "https://foodbattlecolombia.com/wp-content/uploads/2019/12/FEDERICO.jpeg"));
        }
    }
}