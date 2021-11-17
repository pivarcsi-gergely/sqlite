package hu.petrik.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_torles extends AppCompatActivity {

    private EditText edit_id_torles;
    private Button buttonTorles, buttonVissza_torles;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torles);
        init();
        buttonVissza_torles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vissza = new Intent(activity_torles.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
        buttonTorles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String torlendo = edit_id_torles.getText().toString();
                if (torlendo.isEmpty()){
                    Toast.makeText(activity_torles.this, "Nem lehet üres az ID mező!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (db.torles(torlendo) == 0){
                        Toast.makeText(activity_torles.this, "Nincs ilyen ID-jú elem!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(activity_torles.this, "Törlés sikeres", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void init() {
        edit_id_torles = findViewById(R.id.edit_id_torles);
        buttonTorles = findViewById(R.id.buttonTorles);
        buttonVissza_torles = findViewById(R.id.buttonVissza_torles);
        db = new DBHelper(this);
    }
}