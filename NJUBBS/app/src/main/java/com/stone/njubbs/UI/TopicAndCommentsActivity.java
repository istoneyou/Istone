package com.stone.njubbs.UI;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.stone.njubbs.NJUBBSApplication;
import com.stone.njubbs.R;
import com.stone.njubbs.data.Article;

public class TopicAndCommentsActivity extends AppCompatActivity implements TopicAndCommentsActivityFragment.OnListFragmentInteractionListener{

    private static final String ARG_QUERY_URL = "query_url";
    private RequestQueue mQueue;

    @Override
    public void onListFragmentInteraction(Article item) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_and_comments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Article article = null;
        if (getIntent() != null) {
            article = getIntent().getParcelableExtra(ARG_QUERY_URL);
        }
        if (article == null || article.getUrl().isEmpty()) {
            finish();
        }
        getSupportActionBar().setTitle(article.getBoard());
        mQueue = NJUBBSApplication.getRequestQueue();
        TopicAndCommentsActivityFragment fragment = TopicAndCommentsActivityFragment.newInstance(article.getUrl(), mQueue);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
