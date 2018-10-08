package ar.edu.utn.frsf.isi.dam.preferencias2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btnPref;
    private Button btnLeerPref;
    private Button btnEscribirPref;
    private EditText clavePref;
    private EditText valorPref;
    private EditText listaPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        clavePref = (EditText) findViewById(R.id.clavePref);

        valorPref = (EditText) findViewById(R.id.valorPref);

        listaPref = (EditText) findViewById(R.id.listaPref);

        btnPref = (Button) findViewById(R.id.btnPref);
        btnPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this,PreferenciasActivity.class);
                startActivity(i1);
            }
        });

        btnLeerPref = (Button) findViewById(R.id.btnLeerPref);
        btnLeerPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                StringBuilder strBuilder = new StringBuilder();
                Map<String, ?> allEntries = sp.getAll();
                Log.d("APP_PREF","dato "+allEntries.size());
                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    Log.d("APP_PREF","dato "+entry.getKey() + ": " + entry.getValue().toString());

                    strBuilder.append(entry.getKey() + ": " + entry.getValue().toString()+"\n");
                }
                listaPref.setText(strBuilder.toString());
            }
        });

        btnEscribirPref = (Button) findViewById(R.id.btnEscribirPref);
        btnEscribirPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(clavePref.getText().toString(),valorPref.getText().toString());
                editor.commit();
            }
        });

    }
}
