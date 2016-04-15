package umuc.com.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/*File: Personal.java
* Author: Team Bucket List
* Date: 15 April 2016
* Purpose: Sets correct layout view for personal menu. New Personal Goals can be added by
*          selecting floating action button. Extends goals class to handle navigation menu options.
*/

public class Personal extends Goals {

    // On Create method sets activity layout for personal menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Floating action button to add a new goal, on click listener calls popup method
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.plus);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Selected Add", "New personal goal will be created");
                displayPopup();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Sets navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(Personal.this);
    }

    // Creates and displays alert dialog builder
    public void displayPopup() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Personal.this);
        final EditText inputField = new EditText(Personal.this);
        builder.setTitle("Making a New Personal Goal!");
        builder.setMessage("What is your new bucket list item?");
        builder.setView(inputField);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Personal Goals",inputField.getText().toString());
            }
        });

        builder.setNegativeButton("Cancel",null);

        builder.create().show();
    }

}
