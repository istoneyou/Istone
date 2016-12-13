package com.stone.njubbs.UI;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.stone.njubbs.R;

public class NJUBBSMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TopTenFragment.OnFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener{

    private final int NUM_TAB = 2;
    private static final int TAB_INDEX_TOP_TEN = 0;
    private String[] mTabTitles;


    private TabLayout mTabLayout = null;
    private TabsAdapter mTabsAdapter = null;
    private ViewPager mViewPager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nju_bbsmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initTabs();
    }

    private void initTabs() {
        mTabTitles = new String[NUM_TAB];
        mTabTitles[0] = getString(R.string.tab_title_top_ten);
        mTabTitles[1] = "others";
        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mTabsAdapter = new TabsAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabsAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        Log.v("stone", "activity onBackPressed");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nju_bbsmain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class TabsAdapter extends FragmentPagerAdapter {

        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == TAB_INDEX_TOP_TEN)
                return new TopTenFragment().newInstance();
            else return new BlankFragment();
        }

        @Override
        public int getCount() {
            return NUM_TAB;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
