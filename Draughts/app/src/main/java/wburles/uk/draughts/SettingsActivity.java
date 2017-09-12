package wburles.uk.draughts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import wburles.uk.draughts.MySQL.GameDAO;

public class SettingsActivity extends AppCompatActivity {

    GameDAO gameDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Switch gameType = (Switch) findViewById(R.id.switch_gametype);
        final Switch background = (Switch) findViewById(R.id.switch_background);

        gameDatabase = new GameDAO();
        boolean[] settings = gameDatabase.getSettings(getApplicationContext());

        gameType.setChecked(settings[0]);
        background.setChecked(settings[1]);

        gameType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] settings = {gameType.isChecked(), background.isChecked()};
                gameDatabase.setSettings(getApplicationContext(), settings);
            }
        });
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] settings = {gameType.isChecked(),background.isChecked()};
                gameDatabase.setSettings(getApplicationContext(), settings);
            }
        });

    }
}
