package com.ehsan.ehsancontrolco;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_home) {
            // Toast.makeText(this, "home ", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,MainActivity.class));
        }
        else if (id == R.id.menu_settng) {
            // Toast.makeText(this, " setting", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,SettingActivity.class));

        }
        else if (id == R.id.menu_test) {
            // Toast.makeText(this, " test", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,TestActivity.class));
        }
        else if (id == R.id.menu_chart) {
            // Toast.makeText(this, " chart", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,ChartActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
}
