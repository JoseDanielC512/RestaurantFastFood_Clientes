package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.R;
import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.model.entity.Customer;

public class SignUpActivity extends AppCompatActivity {

    private EditText name, email, password, passwordConfirm;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance(); // Instancia de FirebaseAuth para autenticar users
        db = FirebaseFirestore.getInstance(); // Access a Cloud Firestore instance from your Activity

        asignarElementos();
        setTitle(R.string.crear_cuenta);

    }

    public void Btn_Signup(View view){
        validate();
    }

    private void validate(){
        String name_user = name.getText().toString().trim();
        String email_user = email.getText().toString().trim();
        String password_user = password.getText().toString().trim();
        String password_confirmation_user = passwordConfirm.getText().toString().trim();

        if (name_user.isEmpty() || email_user.isEmpty() || password_user.isEmpty() || password_confirmation_user.isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.txt_llenar_campos_login), Toast.LENGTH_SHORT).show();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_user).matches()){
            email.setError("Correo inválido");
            Toast.makeText(getApplicationContext(), "Correo inválido", Toast.LENGTH_SHORT).show();
            return;
        }else{
            email.setError(null);
        }

        if (password_user.length() < 8) {
            password.setError("Se requiere más de 8 caracteres");
            Toast.makeText(getApplicationContext(), R.string.caracteres_minimos, Toast.LENGTH_SHORT).show();
            return;
        }else if (!Pattern.compile("[0-9]").matcher(password_user).find()){
            password.setError("Al menos debe tener un número");
            return;
        }else {
            password.setError(null);
        }

        if (!password_confirmation_user.equals(password_user)){
            passwordConfirm.setError("Deben ser iguales");
            return;
        }else {
            register(email_user, password_user, name_user);
        }
    }

    private void register(String email_user, String password_user, String name_user) {
        mAuth.createUserWithEmailAndPassword(email_user, password_user).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //createUser(email_user, password_user);

                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    String id = currentUser.getUid();
                    Customer customer = new Customer(name_user, email_user);

                    DocumentReference documentReference = db.collection("customers").document(id);

                    documentReference.set(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            Toast.makeText(getApplicationContext(), "Usuario creado exitosamente.", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Registro Fallido.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    });
                }
            }
        });
    }

    public void btn_Login(View view){ // Método para el botón de iniciar sesión
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void asignarElementos(){
        name = findViewById(R.id.et_nombre);
        email = findViewById(R.id.et_email_signin);
        password = findViewById(R.id.et_password_signin);
        passwordConfirm = findViewById(R.id.et_password_signinConfirm);
    }
}