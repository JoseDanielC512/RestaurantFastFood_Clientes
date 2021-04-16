package co.edu.unab.castellanos.jose.restaurantfastfood_clientes.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unab.castellanos.jose.restaurantfastfood_clientes.R;

public class LoginActivity extends AppCompatActivity {

    private EditText et_correo, et_contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        asignarElementos();
        setTitle(R.string.iniciar_sesion);
    }

    public void btn_Login(View view){ // Método para el botón de iniciar sesión
        String user = et_correo.getText().toString();
        String password = et_contraseña.getText().toString();

        if(user.equals(true) && password.equals(true)){

            finish();
            Intent i = new Intent(this, MainActivity.class);
            //i.putExtra("user", this.admin);
            startActivity(i);

        }else if(user.isEmpty() || password.isEmpty()){
            Toast.makeText(this, getString(R.string.txt_llenar_campos_login), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, getString(R.string.txt_datos_erroneos_login), Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_Signin(View view){ // Método para el botón de crear cuenta
        //Intent i = new Intent(this, SigninActivity.class);
        //startActivity(i);
    }

    private void asignarElementos(){
        et_correo = findViewById(R.id.et_email);
        et_contraseña = findViewById(R.id.et_password);
    }
}