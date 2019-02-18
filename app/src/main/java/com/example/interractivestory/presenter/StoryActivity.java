package com.example.interractivestory.presenter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interractivestory.R;
import com.example.interractivestory.model.Page;
import com.example.interractivestory.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private String capturedName;
    private Stack<Integer> pageStack = new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = findViewById(R.id.backgroundImage);
        storyTextView = findViewById(R.id.page0_text);
        choice1Button = findViewById(R.id.choise1_button);
        choice2Button = findViewById(R.id.choise2_button);

        Intent intent = getIntent();
        capturedName = intent.getStringExtra(getString(R.string.key_name));

        if (capturedName == null || capturedName.isEmpty()){
            capturedName = "Friend";
        }

        story = new Story();
        loadPage(0);
    }

    private void loadPage(final int pageNumber) {
        pageStack.push(pageNumber);
        final Page page = story.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        storyImageView.setImageDrawable(image);

        String pageText = getString(page.getTextId());
        pageText = String.format(pageText, capturedName);
        storyTextView.setText(pageText);

        if (page.isFinalPage()) {
            playAgain();
        } else {
            loadButtons(page);
        }
    }

    private void playAgain() {
        choice1Button.setVisibility(View.INVISIBLE);
        choice2Button.setText(getString(R.string.playAgainButton));
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPage(0);
            }
        });
    }

    private void loadButtons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(getString(page.getChoice1().getTextId()));
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });

        choice2Button.setText(getString(page.getChoice2().getTextId()));
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();
        if (pageStack.isEmpty()){
            super.onBackPressed();
        } else {
            loadPage(pageStack.pop());
        }
    }
}
