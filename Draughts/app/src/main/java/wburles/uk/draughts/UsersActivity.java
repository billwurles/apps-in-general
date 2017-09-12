package wburles.uk.draughts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

import wburles.uk.draughts.MySQL.GameDAO;

public class UsersActivity extends AppCompatActivity {
    ArrayList<User> users;
    UserAdapter userAdapter;

    ListView userList;
    Button newUser;

    GameDAO userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        userDB = new GameDAO();
        users = userDB.getUsers(getApplicationContext());

        userList = (ListView) findViewById(R.id.list_users);
        userAdapter = new UserAdapter(getApplicationContext(),users);
        userList.setAdapter(userAdapter);
        newUser = (Button) findViewById(R.id.button_new_user);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewUserFragment newUserFragment = new NewUserFragment();
                Bundle args = new Bundle();
                args.putParcelableArrayList("users",users);
                newUserFragment.setArguments(args);
                newUserFragment.show(getFragmentManager(), "new_user_fragment");
            }
        });
    }

    public void retrieveNewUser(User newUser){
        userDB.addUser(getApplicationContext(),newUser);
        users.add(newUser);
        userAdapter.notifyDataSetChanged();
    }
}