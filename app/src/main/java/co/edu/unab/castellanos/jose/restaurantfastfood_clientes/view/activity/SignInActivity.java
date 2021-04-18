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

public class SignInActivity extends AppCompatActivity {

    private EditText documento, nombre, correo, contraseña, contraseñaConfirmacion;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance(); // Instancia de FirebaseAuth para autenticar users
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

        String nombre_user = nombre.getText().toString();
        String documento_user = documento.getText().toString();
        String correo_user = correo.getText().toString();
        String contraseña_user = contraseña.getText().toString();
        String contraseña_confirmation_user = contraseñaConfirmacion.getText().toString();

        if (nombre_user.isEmpty() || documento_user.isEmpty() || correo_user.isEmpty() || contraseña_user.isEmpty() || contraseña_confirmation_user.isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.txt_llenar_campos_login), Toast.LENGTH_SHORT).show();

        }else if(contraseña_user.length() < 8){
            Toast.makeText(getApplicationContext(), R.string.caracteres_minimos, Toast.LENGTH_SHORT).show();

        }else if (contraseña_user.equals(contraseña_confirmation_user)){
            mAuth.createUserWithEmailAndPassword(correo_user, contraseña_user).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        //updateUI(user);

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(getApplicationContext(), "Usuario creado exitosamente.", Toast.LENGTH_SHORT).show();
                        startActivity(i);

                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        //updateUI(null);
                    }
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }




        /*  Añadir a base de datos pendiente
        Usuario User = new Usuario(nombre_user, documento_user, correo_user, contraseña_user, "");
        usuarios.add(User);
         */


    }

    public void btn_Login(View view){ // Método para el botón de iniciar sesión
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void asignarElementos(){
        nombre = findViewById(R.id.et_nombre);
        documento = findViewById(R.id.et_documento);
        correo = findViewById(R.id.et_email_signin);
        contraseña = findViewById(R.id.et_password_signin);
        contraseñaConfirmacion = findViewById(R.id.et_password_signinConfirm);
    }
}