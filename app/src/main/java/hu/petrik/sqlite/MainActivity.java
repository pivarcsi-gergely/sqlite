package hu.petrik.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnOlvas, btnRogzitesre, btnModositasra, btnTorlesre;
    private TextView textLista;
    private DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnRogzitesre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rogzitesre = new Intent(MainActivity.this, activityRogzites.class);
                startActivity(rogzitesre);
                finish();
            }
        });
        btnOlvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor adatok = db.listazas();
                if (adatok.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Nincs adat az adatbázisban", Toast.LENGTH_SHORT).show();
                }
                else {
                    StringBuilder sb = new StringBuilder();
                    while (adatok.moveToNext()){
                        sb.append("ID: ").append(adatok.getString(0));
                        sb.append(System.lineSeparator());
                        sb.append("Keresztnév: ").append(adatok.getString(1));
                        sb.append(System.lineSeparator());
                        sb.append("Vezetéknév: ").append(adatok.getString(2));
                        sb.append(System.lineSeparator());
                        sb.append("Jegy: ").append(adatok.getString(3));
                        sb.append(System.lineSeparator());
                        sb.append(System.lineSeparator());
                    }
                    textLista.setText(sb.toString());
                }
            }
        });
    }

    private void init() {
        btnOlvas = findViewById(R.id.btn_olvasasra);
        btnRogzitesre = findViewById(R.id.btn_rogzitesre);
        btnModositasra = findViewById(R.id.btn_modositasra);
        btnTorlesre = findViewById(R.id.btn_torlesre);
        textLista = findViewById(R.id.text_lista);
        db = new DBHelper(this);
    }
}