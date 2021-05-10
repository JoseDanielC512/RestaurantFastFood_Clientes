package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.fragment.History;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.fragment.Home;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.fragment.Orders;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.fragment.Profile;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.R;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.entity.Category;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.adapter.CategoryAdapter;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView btn_view;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        toolbar.setTitle(getString(R.string.inicio));
        getFragments(new Home());

        btn_view = findViewById(R.id.bottomNavigationView);

        btn_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        toolbar.setTitle(getString(R.string.inicio));
                        getFragments(new Home());
                        return true;
                    case R.id.orders:
                        toolbar.setTitle(getString(R.string.inicio));
                        getFragments(new Orders());
                        return true;
                    case R.id.history:
                        toolbar.setTitle(getString(R.string.inicio));
                        getFragments(new History());
                        return true;
                    case R.id.profile:
                        toolbar.setTitle(getString(R.string.inicio));
                        getFragments(new Profile());
                        return true;
                }
                return false;
            }
        });
    }

    private void getFragments(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}