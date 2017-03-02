package com.example.oracleicom.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Test activity:
 *
 * Blog post: https://medium.com/google-developers/sharing-content-between-android-apps-2e6db9d1368b
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.launch_activity_button).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra(Main2Activity.EXTRA_DATA, "Hello World!!!");
                startActivity(intent);
                //startActivityForResult(intent, REQUEST_CODE);
            }
        });

        findViewById(R.id.launch_web_button).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Uri uri = Uri.parse("http://theoutline.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.launch_share_button).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent shareIntent = ShareCompat.IntentBuilder.from(MainActivity.this)
                        .setType("text/plain")
                        .setText("Sharing is caring!!!")
                        .getIntent();
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(shareIntent);
                }
            }
        });

        findViewById(R.id.launch_maps_button).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:41.802315,-88.131863");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            TextView messageTextView = (TextView) findViewById(R.id.text_received_message);
            messageTextView.setText(data.getDataString());
        }
    }
}
