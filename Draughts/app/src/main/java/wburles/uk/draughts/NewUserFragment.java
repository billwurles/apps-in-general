package wburles.uk.draughts;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import java.util.ArrayList;

import wburles.uk.draughts.R;
import wburles.uk.draughts.User;
import wburles.uk.draughts.UsersActivity;

public class NewUserFragment extends DialogFragment{
    ArrayList<User> usersArray;

    EditText name;
    EditText desc;
    Button addUser;
    User newUser;

    public NewUserFragment(){}

    @Override
    public void setArguments(Bundle args) {
        usersArray = (ArrayList<User>) args.get("users");
    }

    private boolean validName(String name){
        for(User user : usersArray){
            if(user.getUser().equals(name)){
                return false;
            }
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_user, container);
        getDialog().setTitle(R.string.button_new_user);

        name = (EditText) view.findViewById(R.id.user_name);
        desc = (EditText) view.findViewById(R.id.user_desc);
        addUser = (Button) view.findViewById(R.id.add_user_button);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validName(name.getText().toString())){
                    Toast.makeText(getActivity().getApplicationContext(),"ID is not unique",Toast.LENGTH_LONG).show();
                    Log.d("TaskView", "Attempted to enter invalid id");
                }
                else if(desc.getText().length()==0){
                    Toast.makeText(getActivity().getApplicationContext(),"Descrition field is empty",Toast.LENGTH_LONG).show();
                    Log.d("TaskView", "Attempted to enter empty id");
                }
                else if(name.getText().length()==0){
                    Toast.makeText(getActivity().getApplicationContext(),"Name field is empty",Toast.LENGTH_LONG).show();
                    Log.d("TaskView","Attempted to enter empty name");
                }
                else {
                    newUser = new User(name.getText().toString(), desc.getText().toString(),0);
                    Log.d("TaskView", "newUserFrag is go: " + newUser.toString());
                    ((UsersActivity)getActivity()).retrieveNewUser(newUser);
                    dismiss();
                }
            }
        });
        return view;
    }
}
