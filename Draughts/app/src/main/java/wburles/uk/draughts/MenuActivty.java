package wburles.uk.draughts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import wburles.uk.draughts.MySQL.*;

public class MenuActivty extends AppCompatActivity {
    private final static int NEWGAME = 0;
    private final static int LOADGAME = 1;

    GameDAO gameDatabase;
    GameBoard game;
    private final static GamePiece EMPTY_CELL = new GamePiece(0);

    Button newGameButton, loadGameButton, usersButton, settingsButton;
    boolean gamemode;
    boolean background;

    User[] users;

    public void getUser(User user, int pos){
        users[pos] = user;

        if(pos == 0){
            ViewUserFragment viewUserFragment = new ViewUserFragment();
            Bundle args = new Bundle();
            args.putInt("flag",1);
            args.putParcelable("user",users[0]);
            viewUserFragment.setArguments(args);
            viewUserFragment.show(getFragmentManager(), "view_user_two_fragment");
        } else if(pos==1){
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.putExtra("button", NEWGAME);

            ArrayList<User> temp = new ArrayList<User>();
            temp.add(users[0]);
            temp.add(users[1]);
            users = null;

            intent.putExtra("players", temp);

            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameDatabase = new GameDAO();
        loadGameButton = (Button) findViewById(R.id.button_load_game);
        loadGameButton.setEnabled(true);
        newGameButton = (Button) findViewById(R.id.button_new_game);
        settingsButton = (Button) findViewById(R.id.button_settings);
        usersButton = (Button) findViewById(R.id.button_users);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users = new User[2];
                ViewUserFragment viewUserFragment = new ViewUserFragment();
                Bundle args = new Bundle();
                args.putInt("flag", 0);
                viewUserFragment.setArguments(args);
                viewUserFragment.show(getFragmentManager(), "view_user_fragment");
            }
        });
        loadGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GameBoard tempBoard = new GameBoard(public boolean gameExists(Context context){
                //tempBoard.setEmpty(EMPTY_CELL);
                //tempBoard.setUpBoard();
                //gameDatabase.addBoard(getApplicationContext(), tempBoard.getBoard());

                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("button", LOADGAME);
                startActivity(intent);
            }
        });
        usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UsersActivity.class);
                startActivity(intent);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        background = prefs.getBoolean("BACKGROUND_KEY",false);
        gamemode = prefs.getBoolean("GAMEMODE_KEY",false);

        Log.d("Draughts","oNRSUME IS COOL YO "+gameDatabase.gameExists(getApplicationContext()));

        if(gameDatabase.gameExists(getApplicationContext())){
            loadGameButton.setEnabled(true);
        } else { loadGameButton.setEnabled(true);}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_activty, menu);
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
