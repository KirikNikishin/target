package ru.stankin.labs.sem12;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TargetView targetView;
    private EditText numCirclesInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        targetView = findViewById(R.id.targetView);
        numCirclesInput = findViewById(R.id.numCirclesInput);
        Button applyButton = findViewById(R.id.applyButton);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = numCirclesInput.getText().toString();
                if (!input.isEmpty()) {
                    int numCircles = Integer.parseInt(input);
                    targetView.setNumCircles(numCircles);
                }
            }
        });
    }
}