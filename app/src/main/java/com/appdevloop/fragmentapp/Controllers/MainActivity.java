package com.appdevloop.fragmentapp.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.appdevloop.fragmentapp.R;
import com.appdevloop.fragmentapp.fragments.BlankFragment;
import com.appdevloop.fragmentapp.fragments.DetailFragment;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {

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

    @Override
    public void onButtonClicked(View view) {
        // 1 - Retrieve button tag
        int buttonTag = Integer.parseInt(view.getTag().toString());

        // 2 - Check if DetailFragment is visible (Tablet)
        if (detailFragment != null && detailFragment.isVisible()) {
            // 2.1 - TABLET : Update directly TextView
            detailFragment.updateTextView(buttonTag);
        } else {
            // 2.2 - SMARTPHONE : Pass tag to the new intent that will show DetailActivity (and so DetailFragment)
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra(DetailActivity.EXTRA_BUTTON_TAG, buttonTag);
            startActivity(i);
        }
    }
}
