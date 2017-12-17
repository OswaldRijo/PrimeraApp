package com.oswald.primeraapp;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePicker.OnDateChangedListener, View.OnFocusChangeListener {

    private DatePicker dpFechaNac;
    private TextInputEditText txtInFecNac;
    private TextInputEditText txtInNombre;
    private TextInputEditText txtInTelefono;
    private TextInputEditText txtInEmail;
    private TextInputEditText txtInDescripcion;
    private Button btnAceptar;
    private String fecNac;
    private String nombre;
    private String telefono;
    private String email;
    private String descripcion;



    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dpFechaNac       = (DatePicker) findViewById(R.id.dpFechaNac);
        txtInFecNac      = (TextInputEditText) findViewById(R.id.txtInFechaNac);
        txtInNombre      = (TextInputEditText) findViewById(R.id.txtInNombre);
        txtInTelefono    = (TextInputEditText) findViewById(R.id.txtInTelefono);
        txtInEmail       = (TextInputEditText) findViewById(R.id.txtInEmail);
        txtInDescripcion = (TextInputEditText) findViewById(R.id.txtInDescripcion);
        btnAceptar       = (Button) findViewById(R.id.btnAceptar);

        try{
            Bundle extras = getIntent().getExtras();
            nombre      = extras.getString("nombre");
            fecNac      = extras.getString("fecNac");
            email       = extras.getString("email");
            telefono    = extras.getString("telefono");
            descripcion = extras.getString("descripcion");
            if(!nombre.isEmpty() && !fecNac.isEmpty()   &&
                    !email.isEmpty()  && !telefono.isEmpty() &&
                    !descripcion.isEmpty()){
                txtInFecNac.setVisibility(View.VISIBLE);
                dpFechaNac.setVisibility(View.GONE);
                txtInFecNac.setText(fecNac);
                txtInNombre.setText(nombre);
                txtInTelefono.setText(telefono);
                txtInEmail.setText(email);
                txtInDescripcion.setText(descripcion);

            }

        }catch (Exception e){

        }finally {
            dpFechaNac.setOnDateChangedListener(this);
            txtInFecNac.setOnFocusChangeListener(this);

            btnAceptar.setOnClickListener(this);
        }

    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View view) {
        nombre      = txtInNombre.getText().toString();
        fecNac      = txtInFecNac.getText().toString();
        email       = txtInEmail.getText().toString();
        telefono    = txtInTelefono.getText().toString();
        descripcion = txtInDescripcion.getText().toString();



        if(view == btnAceptar){
            if(!nombre.isEmpty() && !fecNac.isEmpty()   &&
               !email.isEmpty()  && !telefono.isEmpty() &&
               !descripcion.isEmpty()){
                Intent intento = new Intent(MainActivity.this,ConfirmarDatos.class);
                intento.putExtra("nombre",nombre);
                intento.putExtra("fecNac",fecNac);
                intento.putExtra("email",email);
                intento.putExtra("telefono",telefono);
                intento.putExtra("descripcion",descripcion);
                MainActivity.this.finish();
                startActivity(intento);

            }else
                Snackbar.make(btnAceptar, "Debe rellenar todos los campos", Snackbar.LENGTH_LONG).show();

        }

    }

    @Override
    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

            dpFechaNac.setVisibility(View.GONE);
            String fecha ="" + dpFechaNac.getDayOfMonth() + '/' + (dpFechaNac.getMonth()+1)+'/'+dpFechaNac.getYear();
            txtInFecNac.setText(fecha);
            txtInFecNac.setVisibility(View.VISIBLE);

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b && view == txtInFecNac){
            dpFechaNac.setVisibility(View.VISIBLE);
            txtInFecNac.setVisibility(View.GONE);

        }

    }
}
