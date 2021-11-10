package hu.petrik.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnOlvas, btnRogzitesre, btnModositasra, btnTorlesre;
    private TextView textLista;


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
    }

    private void init() {
        btnOlvas = findViewById(R.id.btn_olvasasra);
        btnRogzitesre = findViewById(R.id.btn_rogzitesre);
        btnModositasra = findViewById(R.id.btn_modositasra);
        btnTorlesre = findViewById(R.id.btn_torlesre);
        textLista = findViewById(R.id.text_lista);
    }
}