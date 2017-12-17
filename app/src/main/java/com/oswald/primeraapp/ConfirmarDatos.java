package com.oswald.primeraapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConfirmarDatos extends AppCompatActivity implements View.OnClickListener {


    private String fecNac;
    private String nombre;
    private String telefono;
    private String email;
    private String descripcion;

    private TextView txtNombre;
    private TextView txtFecNac;
    private TextView txtEmail;
    private TextView txtTelefono;
    private TextView txtDescripcion;
    private Button   btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        Bundle extras = getIntent().getExtras();
        nombre      = extras.getString("nombre");
        fecNac      = extras.getString("fecNac");
        email       = extras.getString("email");
        telefono    = extras.getString("telefono");
        descripcion = extras.getString("descripcion");
        btnEditar = (Button) findViewById(R.id.btnEditar);
        
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtFecNac = (TextView) findViewById(R.id.txtFecNac);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtTelefono = (TextView) findViewById(R.id.txtTelefono);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
        
        txtNombre.setText(nombre);
        txtFecNac.setText(fecNac);
        txtEmail.setText(email);
        txtTelefono.setText(telefono);
        txtDescripcion.setText(descripcion);

        btnEditar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btnEditar){
            Intent intento = new Intent(ConfirmarDatos.this,MainActivity.class);
            intento.putExtra("nombre",nombre);
            intento.putExtra("fecNac",fecNac);
            intento.putExtra("email",email);
            intento.putExtra("telefono",telefono);
            intento.putExtra("descripcion",descripcion);
            ConfirmarDatos.this.finish();
            startActivity(intento);
        }
    }
}
