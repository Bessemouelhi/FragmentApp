package com.appdevloop.fragmentapp.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
        //  Configure and show detail fragment
        this.configureAndShowDetailFragment();

        this.configureToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_search:
                Toast.makeText(this, "Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // ----

    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
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
