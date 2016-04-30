package umuc.com.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

/*File: Budgeting.java
* Author: Team Bucket List
* Date: 15 April 2016
* Purpose: Sets correct layout view for budgeting menu. New Budgeting Goals can be added by
*          selecting floating action button. Extends goals class to handle navigation menu options.
*/

public class Budgeting extends Goals {

    // On Create method sets activity layout for budgeting menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgeting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Floating action button to add a new goal, on click listener calls popup method
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.plus);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Selected Add", "New budgeting goal will be created");
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
        navigationView.setNavigationItemSelectedListener(Budgeting.this);

        // Writes text file data to array
        writeArray("budgeting.txt", budgetArray);
        // Updates goals list
        updateGoalList(budgetArray);
    }

    // Updates goal group list view
    // Note: Had to recreate new array with only non-null elements. Feeding array otherwise to
    //       ListView was crashing the app
    public void updateGoalList(String[] goals) {
        int count = 0;

        for (int i = 0; i < goals.length; i++) {
            if (goals[i] != null) {
                count++;
            }
        }
        // Creates new string array
        String goals1[] = new String[count];

        for (int i = 0 ; i < goals.length ; i++)
            if (goals[i] != null) {
                goals1[i] = goals[i];
            }

        final ArrayAdapter<String> theAdapter =
                new ArrayAdapter<String>(this, R.layout.view_goals, R.id.goalTextView, goals1);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(theAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String goalPicked = "You selected " +
                        String.valueOf(adapterView.getItemAtPosition(position));


                Toast.makeText(Budgeting.this, goalPicked, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Creates and displays alert dialog builder
    public void displayPopup() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Title of alertdialog
        builder.setTitle("Enter Your Budget Info");

        // Creates Save Amount to store user input
        final EditText SaveAmt = new EditText(Budgeting.this);

        //User can only input Numbers
        SaveAmt.setInputType(InputType.TYPE_CLASS_NUMBER);
        //Creates a label where user enters amount to save
        SaveAmt.setHint("Enter How Much you want to save");

        //Creates SaveMonth to store user input
        final EditText SaveMonth = new EditText(Budgeting.this);
        //Creates a label where user enters months to save
        SaveMonth.setHint("Months you would like to save?");
        //User can onlt input Numbers
        SaveMonth.setInputType(InputType.TYPE_CLASS_NUMBER);


        LinearLayout lay = new LinearLayout(this);
        lay.setOrientation(LinearLayout.VERTICAL);
        lay.addView(SaveAmt);
        lay.addView(SaveMonth);
        builder.setView(lay);


        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (SaveAmt.getText().length() == 0 || SaveMonth.getText().length() == 0) {
                    Toast.makeText(Budgeting.this, "Enter a Number or a Numbers above 0", Toast.LENGTH_LONG).show();

                } else {


                    Log.d("Budgeting", SaveAmt.getText().toString());
                    Log.d("Budgeting", SaveMonth.getText().toString());


                    int j;
                    int e;

                    i = Integer.parseInt(SaveAmt.getText().toString());
                    e = Integer.parseInt(SaveMonth.getText().toString());

                    Log.d("Budgeting Goals", "You will have to save" + (i / e + "dollar" + "for" + e));

                    Toast.makeText(getApplicationContext(), "Buget Saved. You will have to save " + (i / e + "  dollars") + (" for " + e + " Months"),
                            Toast.LENGTH_LONG).show();
                }

                // Writes text file data to array
                writeArray("budgeting.txt", budgetArray);
                // Updates goals list
                updateGoalList(budgetArray);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}