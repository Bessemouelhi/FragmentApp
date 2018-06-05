package com.appdevloop.fragmentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.appdevloop.fragmentapp.fragments.BlankFragment;
import com.appdevloop.fragmentapp.fragments.DetailFragment;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, DetailFragment.OnFragmentInteractionListener {

    private BlankFragment blankFragment;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2 - Configure and show home fragment
        this.configureAndShowMainFragment();
        // 2 - Configure and show detail fragment
        this.configureAndShowDetailFragment();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i("MainActivity", "onFragmentInteraction: ");
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        startActivity(intent);
    }

    private void configureAndShowMainFragment(){
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        blankFragment = (BlankFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_main);

        if (blankFragment == null) {
            // B - Create new main fragment
            blankFragment = new BlankFragment();
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_main, blankFragment)
                    .commit();
        }
    }

    private void configureAndShowDetailFragment(){
        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail);

        //A - We only add DetailFragment in Tablet mode (If found frame_layout_detail)
        if (detailFragment == null && findViewById(R.id.frame_layout_detail) != null) {
            detailFragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detail, detailFragment)
                    .commit();
        }
    }
}
