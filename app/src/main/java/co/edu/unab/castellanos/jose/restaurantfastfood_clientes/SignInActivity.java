package co.edu.unab.castellanos.jose.restaurantfastfood_clientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    private EditText documento, nombre, correo, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        asignarElementos();
        setTitle(R.string.crear_cuenta);
    }

    public void Btn_Signin(View view){

        String nombre_user = nombre.getText().toString();
        String documento_user = documento.getText().toString();
        String correo_user = correo.getText().toString();
        String contraseña_user = contraseña.getText().toString();

        /*  Añadir a base de datos pendiente
        Usuario User = new Usuario(nombre_user, documento_user, correo_user, contraseña_user, "");
        usuarios.add(User);
         */

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("user", nombre_user);
        startActivity(i);
    }

    private void asignarElementos(){
        nombre = findViewById(R.id.et_nombre);
        documento = findViewById(R.id.et_documento);
        correo = findViewById(R.id.et_email_signin);
        contraseña = findViewById(R.id.et_password_signin);
    }
}