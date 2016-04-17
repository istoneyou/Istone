package com.stone.njubbs.UI;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.stone.njubbs.R;
import com.stone.njubbs.data.Article;

public class TopicAndCommentsActivity extends AppCompatActivity implements TopicAndCommentsActivityFragment.OnListFragmentInteractionListener{

    private static final String ARG_QUERY_URL = "query_url";

    @Override
    public void onListFragmentInteraction(Article item) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_and_comments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String url = "";
        if (getIntent() != null) {
            url = getIntent().getStringExtra(ARG_QUERY_URL);
        }
        if (url == null || url.isEmpty()) {
            finish();
        }
        TopicAndCommentsActivityFragment fragment = TopicAndCommentsActivityFragment.newInstance(url);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
