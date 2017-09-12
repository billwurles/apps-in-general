package wburles.uk.taskview;

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


public class NewUserFragment extends DialogFragment{
    ArrayList<User> usersArray;
    ArrayList<Task> tasksArray;
    Organiser taskData;

    EditText name;
    EditText id;
    Button addUser;
    User newUser;

    public NewUserFragment(){}

    @Override
    public void setArguments(Bundle args) {
        usersArray = (ArrayList<User>) args.get("users");
        tasksArray = (ArrayList<Task>) args.get("tasks");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_user, container);
        getDialog().setTitle(R.string.new_user_title);

        taskData = new Organiser(tasksArray,usersArray);

        for(User user : taskData.getUsers()){
            System.out.println(user.toString());
        }

        name = (EditText) view.findViewById(R.id.user_name);
        id = (EditText) view.findViewById(R.id.user_id);
        addUser = (Button) view.findViewById(R.id.add_user_button);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!taskData.validUserID(id.getText().toString())){
                    Toast.makeText(getActivity().getApplicationContext(),"ID is not unique",Toast.LENGTH_LONG).show();
                    Log.d("TaskView", "Attempted to enter invalid id");
                }
                else if(id.getText().length()==0){
                    Toast.makeText(getActivity().getApplicationContext(),"ID field is empty",Toast.LENGTH_LONG).show();
                    Log.d("TaskView", "Attempted to enter empty id");
                }
                else if(name.getText().length()==0){
                    Toast.makeText(getActivity().getApplicationContext(),"Name field is empty",Toast.LENGTH_LONG).show();
                    Log.d("TaskView","Attempted to enter empty name");
                }
                else {
                    newUser = new User(name.getText().toString(), id.getText().toString());
                    Log.d("TaskView", "newUserFrag is go: " + newUser.toString());
                    ((TaskViewActivity)getActivity()).retrieveNewUser(newUser);
                    dismiss();
                }
            }
        });
        return view;
    }
}
