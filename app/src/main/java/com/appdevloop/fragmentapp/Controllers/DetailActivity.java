package com.appdevloop.fragmentapp.Controllers;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.appdevloop.fragmentapp.R;
import com.appdevloop.fragmentapp.fragments.BlankFragment;
import com.appdevloop.fragmentapp.fragments.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    // 1 - Create static variable to identify Intent
    public static final String EXTRA_BUTTON_TAG = "EXTRA_BUTTON_TAG";
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.configureAndShowMainFragment();

        this.configureToolbar();
    }

    private void configureAndShowMainFragment(){
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail);

        if (detailFragment == null) {
            // B - Create new main fragment
            detailFragment = new DetailFragment();
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detail, detailFragment)
                    .commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // 3 - Call update method here because we are sure that DetailFragment is visible
        this.updateDetailFragmentTextViewWithIntentTag();
    }

    // --------------
    // UPDATE UI
    // --------------

    // 2 - Update DetailFragment with tag passed from Intent
    private void updateDetailFragmentTextViewWithIntentTag(){
        // Get button's tag from intent
        int buttonTag = getIntent().getIntExtra(EXTRA_BUTTON_TAG, 0);
        // Update DetailFragment's TextView
        detailFragment.updateTextView(buttonTag);
    }

    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
