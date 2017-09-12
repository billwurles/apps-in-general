package wburles.uk.scoreboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 14038690 on 29/02/2016.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new Settings()).commit();

    }
}
