package com.zpauly.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zpauly.topnavigation.OnNavItemClickedListener;
import com.zpauly.topnavigation.TopNavigation;
import com.zpauly.topnavigation.TopNavigationBuilder;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Button mDefaultButton;
    private Button mCustomButton0;
    private Button mCustomButton1;

    private TopNavigation mDefaultTN;
    private TopNavigation mCustomTN0;
    private TopNavigation mCustomTN1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDefaultButton = (Button) findViewById(R.id.default_BTN);
        mCustomButton0 = (Button) findViewById(R.id.custom_BTN0);
        mCustomButton1 = (Button) findViewById(R.id.custom_BTN1);

        initTopNavigations();

        mDefaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDefaultTN.open();
            }
        });
        mCustomButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomTN0.open();
            }
        });
        mCustomButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomTN1.open();
            }
        });
    }

    private void initTopNavigations() {
        mDefaultTN = TopNavigationBuilder.withActivity(this)
                .closableOutside(true)
                .titles(Arrays.asList("item 0", "item 1", "item 2", "item 3", "item 4"))
                .build();

        mDefaultTN.getDefaultAdapter().setOnNavItemClickedListener(new OnNavItemClickedListener() {
            @Override
            public void onClicked(View v, int position) {
                Toast.makeText(MainActivity.this, "item" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mCustomTN0 = TopNavigationBuilder.withActivity(this)
                .closableOutside(true)
                .build();

        mCustomTN1 = TopNavigationBuilder.withActivity(this)
                .closableOutside(true)
                .animDuration(2000)
                .build();

        mCustomTN0.resetAdapter(new CustomAdapter(this, 0));
        mCustomTN1.resetAdapter(new CustomAdapter(this, 1));
    }
}
