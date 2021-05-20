package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.R;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.entity.Customer;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView full_name, direction, email, phone, score, logout;
    private ImageView iv_logout, iv_picProfile;
    private Customer customer;

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        auth = FirebaseAuth.getInstance(); // Instancia de FirebaseAuth para autenticar users
        db = FirebaseFirestore.getInstance(); // Access a Cloud Firestore instance from your Activity
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        associateElements(view);
        getDataUser();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auth.getCurrentUser() != null){
                    auth.signOut();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        iv_picProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auth.getCurrentUser() != null){
                    auth.signOut();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    private void associateElements(View view) {
        full_name = view.findViewById(R.id.tv_name_profile);
        direction = view.findViewById(R.id.tv_dir_profile);
        email = view.findViewById(R.id.tv_email_profile);
        phone = view.findViewById(R.id.tv_phone_profile);
        score = view.findViewById(R.id.tv_score_profile);
        logout = view.findViewById(R.id.tv_logout_profile);
        iv_logout = view.findViewById(R.id.iv_logout_profile);
        iv_picProfile = view.findViewById(R.id.iv_pic_profile);
    }

    private void getDataUser(){

        FirebaseUser currentUser = auth.getCurrentUser();
        String id = currentUser.getUid();
        db.collection("customers").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    customer = task.getResult().toObject(Customer.class);

                    Integer num = customer.getScore();
                    String score2 = num.toString();

                    full_name.setText(customer.getName());
                    direction.setText(customer.getDir());
                    email.setText(customer.getEmail());
                    phone.setText(customer.getPhone());
                    score.setText(score2);
                }
            }
        });
    }
}