package com.example.oracleicom.intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public static final String EXTRA_DATA = "data";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView messageTextView = (TextView) findViewById(R.id.text_message);
        if (getIntent().hasExtra(EXTRA_DATA)) {
            messageTextView.setText(getIntent().getStringExtra(EXTRA_DATA));
        }
    }

    @Override public void finish() {
        Intent data = new Intent();
        data.setData(Uri.parse(Main2Activity.class.getSimpleName()));
        setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
