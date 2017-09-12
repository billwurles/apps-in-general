package wburles.uk.scoreboard;

import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class scoreboard extends AppCompatActivity {
    EditText player1Edit;
    EditText player2Edit;
    Button buttonP1;
    Button buttonP2;
    Button reset;

    int p1 = 0;
    int p2 = 0;
    String nameP1;
    String nameP2;

    int increment;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);



        buttonP1 = (Button) findViewById(R.id.button_player1);
        buttonP2 = (Button) findViewById(R.id.button_player2);
        player1Edit = (EditText) findViewById(R.id.edit_player1);
        player2Edit = (EditText) findViewById(R.id.edit_player2);

        reset = (Button) findViewById(R.id.reset_button);

        buttonP1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                p1+=increment;
                buttonP1.setText(Integer.toString(p1));
            }
        });

        buttonP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2 += increment;
                buttonP2.setText(Integer.toString(p2));
            }
        });

        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Resetting "+value,Toast.LENGTH_LONG).show();
                p1 = value;
                p2 = value;
                buttonP1.setText(Integer.toString(p1));
                buttonP2.setText(Integer.toString(p2));
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor sharedEditor = sharedPrefs.edit();

        nameP1 = String.valueOf(player1Edit.getText());
        nameP2 = String.valueOf(player2Edit.getText());

        editor.putString("PLAYER_1_NAME_KEY",nameP1);
        editor.putString("PLAYER_2_NAME_KEY",nameP2);
        editor.putInt("PLAYER_1_SCORE_KEY", p1);
        editor.putInt("PLAYER_2_SCORE_KEY", p2);
        sharedEditor.putInt("INCREMENT_KEY",increment);
        sharedEditor.putInt("START_KEY",value);

        editor.commit();
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        p1 = prefs.getInt("PLAYER_1_SCORE_KEY",0);
        p2 = prefs.getInt("PLAYER_2_SCORE_KEY",0);

        buttonP1.setText(Integer.toString(p1));
        buttonP2.setText(Integer.toString(p2));

        nameP1 = prefs.getString("PLAYER_1_NAME_KEY", "");
        nameP2 = prefs.getString("PLAYER_2_NAME_KEY","");

        player1Edit.setHint(nameP1);
        player2Edit.setHint(nameP2);

        increment = sharedPrefs.getInt("INCREMENT_KEY",0);
        value = sharedPrefs.getInt("START_KEY",0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scoreboard, menu);
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
}

