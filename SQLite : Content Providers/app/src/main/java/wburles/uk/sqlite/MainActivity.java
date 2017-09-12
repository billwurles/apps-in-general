package wburles.uk.sqlite;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView birdView;
    ArrayAdapter birdAdapter;
    ArrayList<Bird> birds;
    BirdDAO birdDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        birdDatabase = new BirdDAO(this);
        birdView = (ListView) findViewById(R.id.bird_list);
        birdDatabase.open();
        //

        birds = new ArrayList<Bird>();

        birdAdapter = new ArrayAdapter<Bird>(this,android.R.layout.simple_list_item_1,birds);
        birdView.setAdapter(birdAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Log.d("BIRDS", "Result: " + extras.getString("binomial")+" "+ extras.getString("common"));
            birdDatabase.addBird(extras.getString("binomial"), extras.getString("common"));
        }

    }

    @Override
    protected void onResume() {

        super.onResume();

        birdDatabase.getBirds(birds);
        Log.d("BIRDS", "size of array is: "+birds.size());
        birdAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent = new Intent(this, AddBirdActivity.class);
            startActivityForResult(intent, 1);
        }

        return super.onOptionsItemSelected(item);
    }
}
