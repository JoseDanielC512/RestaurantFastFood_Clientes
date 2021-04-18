package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private EditText et_correo, et_contraseña;
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
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            //i.putExtra("user", this.admin);
            startActivity(i);
            finish();
            Toast.makeText(getApplicationContext(), "Bienvenido " + currentUser.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_Login(View view){ // Método para el botón de iniciar sesión
        String email = et_correo.getText().toString();
        String password = et_contraseña.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.txt_llenar_campos_login), Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        //updateUI(user);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), getString(R.string.txt_datos_erroneos_login), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Authentication failed.",Toast.LENGTH_SHORT).show();
                        //updateUI(null);
                    }
                }
            });
        }




    }

    public void btn_Signin(View view){ // Método para el botón de crear cuenta
        Intent i = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(i);
        finish();
    }

    public void btn_ForgotPassword(View view){ // Método para el botón de crear cuenta
        //
    }

    private void asignarElementos(){
        et_correo = findViewById(R.id.et_email);
        et_contraseña = findViewById(R.id.et_password);
    }
}