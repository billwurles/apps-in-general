package wburles.uk.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddBirdActivity extends AppCompatActivity {
    EditText binomialInput;
    EditText commonInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bird);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        binomialInput = (EditText) findViewById(R.id.binomial_name);
        commonInput = (EditText) findViewById(R.id.common_name);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addbird, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            Intent result = new Intent();
            result.putExtra("binomial",binomialInput.getText().toString());
            result.putExtra("common",commonInput.getText().toString());
            Log.d("BIRDS", "this is running");
            setResult(RESULT_OK, result);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
