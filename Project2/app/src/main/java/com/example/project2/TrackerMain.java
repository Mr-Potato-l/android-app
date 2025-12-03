package com.example.project2;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.project2.MinecraftServerHandler.post;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.Objects;


public class TrackerMain extends AppCompatActivity {

    Button startTracking;
    EditText ipInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker_main);

        startTracking = findViewById(R.id.getStatsBtn);
        ipInput = findViewById(R.id.ipInput);

        startTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputIP = ipInput.getText().toString();
                if(inputIP.length() == 0)
                    return;
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Looper.prepare();
                            Toast.makeText(TrackerMain.this, post(inputIP), LENGTH_SHORT).show();
                        } catch (IOException e) {
                            Log.e("TrackerMain", Objects.requireNonNull(e.getMessage()));
                        }
                        Looper.loop();
                    }
                });
                t.start();
            }
        });
    }
}