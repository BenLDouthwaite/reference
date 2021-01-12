package com.example.ben.myapplication.SavingData;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ben.myapplication.R;

public class SavingKeyValueSetsActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button b1;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_key_value_sets);



        b1=(Button)findViewById(R.id.button);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saving_key_value_sets, menu);
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

    public void writeKeyValueSet(View view){
        SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);
        String n  = ed1.getText().toString();
        String ph  = ed2.getText().toString();
        String e  = ed3.getText().toString();

        editor.putString(Name, n);
        editor.putString(Phone, ph);
        editor.putString(Email, e);
        //editor.putInt("idName", 12); //Write Int instead of String example
        editor.commit();

        Toast.makeText(SavingKeyValueSetsActivity.this, "Data Written", Toast.LENGTH_LONG).show();
    }

    public void readKeyValueSet(View view){
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        String name = prefs.getString(Name, "No name defined");//"No name defined" is the default value.
        String phone = prefs.getString(Phone, "No phone defined");//"No name defined" is the default value.
        String email = prefs.getString(Email, "No email defined");//"No name defined" is the default value.
        //int idName = prefs.getInt("idName", 0); //0 is the default value. //Read int instead of String example

        TextView nameText =(TextView)findViewById(R.id.textView3);
        nameText.setText(name);
        TextView phoneText =(TextView)findViewById(R.id.textView4);
        phoneText.setText(phone);
        TextView emailText =(TextView)findViewById(R.id.textView5);
        emailText.setText(email);
    }
}
