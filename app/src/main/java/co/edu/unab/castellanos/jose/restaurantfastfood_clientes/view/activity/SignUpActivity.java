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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText document, name, email, password, passwordConfirm;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance(); // Instancia de FirebaseAuth para autenticar users
        db = FirebaseFirestore.getInstance(); // Access a Cloud Firestore instance from your Activity

        asignarElementos();
        setTitle(R.string.crear_cuenta);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
        }
    }

    public void Btn_Signin(View view){
        validate();


        /*  Añadir a base de datos pendiente
        Usuario User = new Usuario(nombre_user, documento_user, correo_user, contraseña_user, "");
        usuarios.add(User);
         */


    }

    private void createUser(String email, String name, String document, String password) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("document", document);
        user.put("name", name);
        user.put("password", password);
        user.put("score", 0);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = currentUser.getUid();

        DocumentReference documentReference = db.collection("customers").document(id);

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Almacené usuario", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "No Almacené usuario", Toast.LENGTH_LONG).show();
            }
        });


        /*
        db.collection("customers")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Almacené usuario", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "No Almacené usuario", Toast.LENGTH_LONG).show();
                    }
                });

         */
    }

    private void validate(){
        String name_user = name.getText().toString().trim();
        String document_user = document.getText().toString().trim();
        String email_user = email.getText().toString().trim();
        String password_user = password.getText().toString().trim();
        String password_confirmation_user = passwordConfirm.getText().toString().trim();

        if (name_user.isEmpty() || document_user.isEmpty() || email_user.isEmpty() || password_user.isEmpty() || password_confirmation_user.isEmpty()){
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
            register(email_user, password_user, name_user, document_user);
        }
    }

    private void register(String email_user, String password_user, String name_user, String document_user) {
        mAuth.createUserWithEmailAndPassword(email_user, password_user).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    createUser(email_user, name_user, document_user, password_user);
                    // Sign in success, update UI with the signed-in user's information
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    Toast.makeText(getApplicationContext(), "Usuario creado exitosamente.", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    finish();

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(), "Registro Fallido.", Toast.LENGTH_SHORT).show();
                    //updateUI(null);
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
        document = findViewById(R.id.et_documento);
        email = findViewById(R.id.et_email_signin);
        password = findViewById(R.id.et_password_signin);
        passwordConfirm = findViewById(R.id.et_password_signinConfirm);
    }
}