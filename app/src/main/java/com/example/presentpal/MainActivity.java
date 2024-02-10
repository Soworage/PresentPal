package com.example.presentpal;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = findViewById(R.id.imageButton11);

        // Prüfen, ob der ImageButton klickbar ist
        if (imageButton.isClickable()) {
            Toast.makeText(this, "Der ImageButton ist klickbar.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Der ImageButton ist nicht klickbar.", Toast.LENGTH_SHORT).show();
        }

        // Klick-Event für den ImageButton setzen
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ImageButton wurde geklickt!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
