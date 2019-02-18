package com.example.interractivestory.presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.interractivestory.R;

public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private Button startAdventure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.enterNameEditText);
        startAdventure = findViewById(R.id.startButton);

        startAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String capturedName = nameField.getText().toString();
                // Toast.makeText(MainActivity.this, capturedName, Toast.LENGTH_SHORT).show();
                startStory(capturedName);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Reset name field on resume activity
        nameField.setText("");
    }

    private void startStory(String capturedName) {
        // StoryActivity.class to reffer to the class in execution rather than just the class name
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra("name", capturedName);
        startActivity(intent);
    }

}
