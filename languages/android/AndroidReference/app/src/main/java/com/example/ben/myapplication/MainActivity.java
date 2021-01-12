package com.example.ben.myapplication;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

import com.example.ben.myapplication.Graphics.GraphicsMenuActivity;
import com.example.ben.myapplication.SavingData.SavingDataMenuActivity;
import com.example.ben.myapplication.UserInterface.GridLayoutExampleActivity;
import com.example.ben.myapplication.UserInterface.RelativeLayoutExampleActivity;
import com.example.ben.myapplication.UserInterface.UserInterfaceMenuActivity;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.ben.myapplication.MESSAGE";
    public final static String TAG = "debugMessage";


    //Main screen, R.layout.activity_main = the main screen.
    //First thing done when activity created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//CHOOSE LAYOUT TO DISPLAY
        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    //Top Activity Bar . can add via code or via menu.xml file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);

        // It is also possible add items here. Use a generated id from
        // resources (ids.xml) to ensure that all menu ids are distinct.
        MenuItem locationItem = menu.add(0, R.id.menu_location, 0, R.string.menu_location);
        locationItem.setIcon(R.drawable.ic_action_location);
        MenuItem locationItem2 = menu.add(0, R.id.menu_location2, 0, R.string.menu_location);
        locationItem2.setIcon(R.drawable.ic_action_location);

        // Need to use MenuItemCompat methods to call any action item related methods
        MenuItemCompat.setShowAsAction(locationItem, MenuItem.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setShowAsAction(locationItem2, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);

        return super.onCreateOptionsMenu(menu);
    }

    //Reaction to action bar options being selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                Intent intent = new Intent(this, SlidingTabsActivity.class);
                startActivity(intent);

                return true;

            case R.id.menu_location:
                // Here we might call LocationManager.requestLocationUpdates()
                return true;

            case R.id.menu_settings:
                // Here we would open up our settings activity
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/

    public void openGraphicsMenu(View view) {
        Intent intent = new Intent(this, GraphicsMenuActivity.class);
        startActivity(intent);
    }

    public void openUserInterfaceMenu(View view) {
        Intent intent = new Intent(this, UserInterfaceMenuActivity.class);
        startActivity(intent);
    }

    public void openSavingDataMenu(View view){
        Intent intent = new Intent(this, SavingDataMenuActivity.class);
        startActivity(intent);
    }

//    public void openDesignPatternsMenu(View view){
//        Intent intent = new Intent(this, DesignPatternsManuActivity.class);
//        startActivity(intent);
//    }
}
