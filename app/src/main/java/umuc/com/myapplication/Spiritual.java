package umuc.com.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.DatePicker;

/*File: Spiritual.java
* Author: Team Bucket List
* Date: 15 April 2016
* Purpose: Sets correct layout view for spiritual menu. New Spiritual Goals can be added by
*          selecting floating action button. Extends goals class to handle navigation menu options.
*/

public class Spiritual extends Goals {

    // On Create method sets activity layout for spiritual menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiritual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Floating action button to add a new goal, on click listener calls popup method
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.plus);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Selected Add", "New spiritual goal will be created");
                displayPopup();
                displaycal();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Sets navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(Spiritual.this);
    }

        builder.setTitle("Set your Deadline for your new Goal");
        builder.setView(picker);
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id){
                Log.d("Spiritual Goals", DateInput.getText().toString());
            }
        });
        builder.show();
    }
    // Creates and displays alert dialog builder
    public void displayPopup() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Spiritual.this);
        final EditText inputField = new EditText(Spiritual.this);
        builder.setTitle("Making a New Spritual Goal!");
        builder.setMessage("What is your new bucket list item?");
        builder.setView(inputField);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Spiritual Goals", inputField.getText().toString());
            }
        });

        builder.setNegativeButton("Cancel", null);

        builder.create().show();
    }
}
