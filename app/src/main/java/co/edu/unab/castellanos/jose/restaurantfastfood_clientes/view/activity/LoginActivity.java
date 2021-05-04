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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        asignarElementos();
        setTitle(R.string.iniciar_sesion);

        //FirebaseAuth.getInstance().signOut(); // Método para cerrar la sesion (debe ponerse en un menu)

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Bienvenido " + currentUser.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_Login(View view){ // Método para el botón de iniciar sesión
        validate();

    }

    private void validate(){
        String email_user = email.getText().toString().trim();
        String password_user = password.getText().toString().trim();

        if(email_user.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email_user).matches()){
            email.setError("Correo inválido");
            Toast.makeText(getApplicationContext(), "Correo inválido", Toast.LENGTH_SHORT).show();
            return;
        }else {
            email.setError(null);
        }

        if (password_user.isEmpty()) {
            password.setError("Escribe la contraseña");
            return;
        }else {
            password.setError(null);
            entry(email_user, password_user);
        }
    }

    private void entry(String email_user, String password_user) {
        mAuth.signInWithEmailAndPassword(email_user, password_user).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(), getString(R.string.txt_datos_erroneos_login), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void btn_Signup(View view){ // Método para el botón de crear cuenta
        Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(i);
        finish();
    }

    public void btn_ForgotPassword(View view){ // Método para el botón de olvidaste contraseña
        Intent i = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
        startActivity(i);
        finish();
    }

    private void asignarElementos(){
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
    }
}