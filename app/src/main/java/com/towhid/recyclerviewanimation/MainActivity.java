package com.towhid.recyclerviewanimation;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Filterable {

    RecyclerView NewRecyclerView;
    NewsAdapter newsAdapter;
    List<NewsItem> mData;
    FloatingActionButton fab_switcher;
    boolean isDark = false;
    ConstraintLayout root_layout;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // hide action ber
        getSupportActionBar().hide();


        fab_switcher = (FloatingActionButton) findViewById(R.id.fab_switcher);
        root_layout = (ConstraintLayout) findViewById(R.id.root_layout);
        NewRecyclerView = (RecyclerView) findViewById(R.id.news_rv);
        search = (EditText) findViewById(R.id.search);
        mData = new ArrayList<>();


        isDark = getThemeStatePref();
        if (isDark) {

            search.setBackgroundResource(R.drawable.search_input_dark_style);
            root_layout.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            search.setBackgroundResource(R.drawable.search_input_style);
            root_layout.setBackgroundColor(getResources().getColor(R.color.white));

        }


        for (int i = 0; i < 100; i++) {
            mData.add(new NewsItem("I love O2 You Love me thank you", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. .", "10 Jan,2019", R.drawable.im1));
            mData.add(new NewsItem("I love O3 You Love me thank you", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.", "10 Jan,2019", R.drawable.im2));
            mData.add(new NewsItem("I love O4 You Love me thank you", "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "10 Jan,2019", R.drawable.im3));
            mData.add(new NewsItem("I love O5 You Love me thank you", "When an unknown printer took a galley of type and scrambled it to make a type specimen book.", "10 Jan,2019", R.drawable.im4));
        }


        newsAdapter = new NewsAdapter(this, mData, isDark);

        NewRecyclerView.setAdapter(newsAdapter);
        NewRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        fab_switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDark = !isDark;
                if (isDark) {
                    root_layout.setBackgroundColor(getResources().getColor(R.color.black));
                    search.setBackgroundResource(R.drawable.search_input_dark_style);
                } else {
                    search.setBackgroundResource(R.drawable.search_input_style);
                    root_layout.setBackgroundColor(getResources().getColor(R.color.white));
                }
                newsAdapter = new NewsAdapter(getApplicationContext(), mData, isDark);
                NewRecyclerView.setAdapter(newsAdapter);

                saveThemeStatePref(isDark);

            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newsAdapter.getFilter().filter(charSequence);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void saveThemeStatePref(boolean isDark) {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isDark", isDark);
        editor.commit();

    }

    private boolean getThemeStatePref() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        boolean isDar = pref.getBoolean("isDark", false);
        return isDar;
    }


    @Override
    public Filter getFilter() {
        return null;
    }
}
