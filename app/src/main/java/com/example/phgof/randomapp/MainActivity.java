package com.example.phgof.randomapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import java.util.Random;

public class MainActivity extends Activity {
    private TextSwitcher mSwitcher;
    private int mCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the TextSwitcher view from the layout
        mSwitcher = findViewById(R.id.switcher);
        // BEGIN_INCLUDE(setup)
        // Set the factory used to create TextViews to switch between.
        mSwitcher.setFactory(mFactory);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
        // END_INCLUDE(setup)


        Button btnroll = findViewById(R.id.button);
        Button btncopy = findViewById(R.id.button2);
        Button btnreset = findViewById(R.id.button3);
        final TextView tv1 = findViewById(R.id.tv1);
        final TextView tvswitch = (TextView) mSwitcher.getCurrentView();

        btnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter++;
                 String result = randomtext(8);
                {    // BEGIN_INCLUDE(settext)
                    mSwitcher.setText(String.valueOf(result));
                    tv1.setText(String.valueOf("ROUND: "+ mCounter));
                }   // END_INCLUDE(settext)

            }
        });
            //Copy value
        btncopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to Copy the content of Text View to the Clip board.
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", tvswitch.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(),"Copied to Clipboard",
                        Toast.LENGTH_LONG).show();
            }

        });
            // Reset value
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter = 0;
                String data = "";
                mSwitcher.setText(String.valueOf(data));
                tv1.setText(String.valueOf("Round: "+ mCounter));
            }

        });
        // Set the initial text without an animation
        mSwitcher.setCurrentText(String.valueOf(mCounter));

    }

    public static String randomtext(int length){
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int n = str.length();
        StringBuilder result = new StringBuilder();
        Random r = new Random();

        for (int i=0; i<length; i++)
            result.append(str.charAt(r.nextInt(n)));
        return result.toString();
    }


    private ViewFactory mFactory = new ViewFactory() {

        @Override
        public View makeView() {
            // Create a new TextView
            TextView t = new TextView(MainActivity.this);
            t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            t.setTextAppearance(MainActivity.this, android.R.style.TextAppearance_Large);
            return t;
        }
    };
    // END_INCLUDE(factory)
}

