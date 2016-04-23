package umuc.com.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*File: Personal.java
* Author: Team Bucket List
* Date: 15 April 2016
* Purpose: Sets correct layout view for personal menu. New Personal Goals can be added by
*          selecting floating action button. Extends goals class to handle navigation menu options.
*/

public class Personal extends Goals {

    /* create text file to store list of personal goal names */
    public final static String PERSLABELS = "perslabels.txt";

    /* create text file to store list of dates for personal goals */
    public final static String PERSDATES = "persdates.txt";



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
        navigationView.setNavigationItemSelectedListener(Personal.this);
    }

    // Displays date picker to keep track of deadlines for goals
    public void displaycal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        DatePicker picker = new DatePicker(this);
        final EditText DateInput = new EditText(Personal.this);


        builder.setTitle("Set your Deadline for your new Goal");
        builder.setView(picker);
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                displayPopup();
                Log.d("Personal Goals", DateInput.getText().toString());
                try {
                    OutputStreamWriter out =
                            new OutputStreamWriter(openFileOutput(PERSDATES, MODE_APPEND));
                    out.write(DateInput.getText().toString() + '\n');
                    out.close();
                    Toast.makeText(getApplicationContext(), "Date saved in the file.",
                            Toast.LENGTH_LONG).show();

                } catch (Throwable t) {
                    Toast.makeText(getApplicationContext(), "Exception: " + t.toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.show();
    }

    // Creates and displays alert dialog builder
    public void displayPopup() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Personal.this);
        final EditText inputField = new EditText(Personal.this);
        builder.setTitle("Making a New Personal Goal!");
        builder.setMessage("What is your new bucket list item?");
        builder.setView(inputField);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Personal Goals", inputField.getText().toString() + '\n');
                try {
                    OutputStreamWriter out =
                            new OutputStreamWriter(openFileOutput(PERSLABELS, MODE_APPEND));
                    out.write(inputField.getText().toString() + "\n");
                    out.close();
                    Toast.makeText(getApplicationContext(), "Personal goal saved to file.",
                            Toast.LENGTH_LONG).show();

                } catch (Throwable t) {
                    Toast.makeText(getApplicationContext(), "Exception: " + t.toString(),
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        builder.setNegativeButton("Cancel", null);

        builder.create().show();
    }

}
