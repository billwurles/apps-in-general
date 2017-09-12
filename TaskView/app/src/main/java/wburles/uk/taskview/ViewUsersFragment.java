package wburles.uk.taskview;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Will on 11/03/2016.
 */
public class ViewUsersFragment extends DialogFragment {

    ArrayList<User> usersArray;
    ArrayList<Task> tasksArray;
    Organiser taskData;

    ListView userList;
    ArrayAdapter<User> userAdapter;
    int arg;
    private final static int VIEW_USERS_TASKS = 2;

    public ViewUsersFragment(){}

    @Override
    public void setArguments(Bundle args) {
        arg = args.getInt("arg");
        usersArray = (ArrayList<User>) args.get("users");
        tasksArray = (ArrayList<Task>) args.get("tasks");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_users, container);
        getDialog().setTitle(R.string.user_title);

        taskData = new Organiser(tasksArray,usersArray);

        userList = (ListView) view.findViewById(R.id.fragment_user_list);
        userAdapter = new ArrayAdapter<User>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, usersArray);
        userList.setAdapter(userAdapter);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arg == 1) { //if from NewTaskActivity
                    Log.d("TaskView", "Retrieving user for new task: " + usersArray.get(position).getID());
                    ((NewTaskActivity) getActivity()).retrieveTeamMember(usersArray.get(position).getID());
                    dismiss();
                }
                if (arg == 2) { //if from TaskViewActivity
                    Log.d("TaskView", "Viewing users from TaskView: " + usersArray.get(position).getID());
                    ArrayList<Task> usersTasks = taskData.findUserTasks(usersArray.get(position).getID());
                    Intent intent = new Intent(getActivity().getBaseContext(), ViewUsersTasksActivity.class);
                    intent.putExtra("title","Tasks for: "+usersArray.get(position).getUser());
                    intent.putParcelableArrayListExtra("tasks", usersTasks);
                    intent.putParcelableArrayListExtra("users", taskData.getUsers());
                    startActivityForResult(intent, VIEW_USERS_TASKS);
                }
                if (arg == 3){
                    dismiss();
                }
            }
        });
        return view;
    }
}
