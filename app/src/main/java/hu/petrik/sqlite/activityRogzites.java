package hu.petrik.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activityRogzites extends AppCompatActivity {
    private Button btn_rogzit, btn_vissza;
    private EditText edit_vNev, edit_kNev, edit_jegy;
    private DBHelper adatbazis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rogzites);
        init();
        btn_vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vissza = new Intent(activityRogzites.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
        btn_rogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vezetnev = edit_vNev.getText().toString().trim();
                String keresztnev = edit_kNev.getText().toString().trim();
                String jegy = edit_jegy.getText().toString().trim();
                if (vezetnev.isEmpty() || keresztnev.isEmpty() || jegy.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        int jegySzam = Integer.parseInt(jegy);
                        if (jegySzam < 1 || jegySzam > 5) {
                            Toast.makeText(getApplicationContext(), "A jegynek 1 és 5 között kell lennie!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (adatbazis.rogzites(vezetnev, keresztnev, jegySzam) ) {
                                Toast.makeText(getApplicationContext(), "Sikeres rögzítés", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Sikertelen rögzítés", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    catch (NumberFormatException n) {
                        Toast.makeText(getApplicationContext(), "A jegynek számnak kell lennie", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void init() {
        btn_rogzit = findViewById(R.id.btn_rogzit);
        btn_vissza = findViewById(R.id.btn_vissza);
        edit_vNev = findViewById(R.id.edit_vNev);
        edit_kNev = findViewById(R.id.edit_kNev);
        edit_jegy = findViewById(R.id.edit_jegy);
        adatbazis = new DBHelper(this);
    }
}