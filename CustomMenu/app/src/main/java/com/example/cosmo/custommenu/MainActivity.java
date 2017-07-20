package com.example.cosmo.custommenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.item1){
            Log.d("Option", "Option 1 was clicked");
        }
        if(item.getItemId() == R.id.item2){
            Toast.makeText(this, "item2", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}