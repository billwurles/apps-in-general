package wburles.uk.taskview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class ViewUsersTasksActivity extends AppCompatActivity {
    private Intent intent;

    private Organiser taskData;
    private ArrayList<Task> tasksArray = new ArrayList<Task>();
    private ArrayList<User> usersArray = new ArrayList<User>();
    private ArrayAdapter<Task> taskAdapter;

    private ListView taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users_tasks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intent = getIntent();
        tasksArray = intent.getParcelableArrayListExtra("tasks");
        usersArray = intent.getParcelableArrayListExtra("users");
        setTitle(intent.getExtras().getString("title"));
        for(Task task : tasksArray){
            task.setUserHours();
        }
        Collections.sort(tasksArray,new DateSorter());
        taskData = new Organiser(tasksArray,usersArray);
        taskList = (ListView) findViewById(R.id.users_task_list);

        taskAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, tasksArray);
        taskList.setAdapter(taskAdapter);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = tasksArray.get(position);
                ArrayList<User> team = new ArrayList<User>();
                for(String userID : task.getTeam()){
                    int pos = taskData.getUserPos(userID);
                    if(pos>=0){
                        team.add(taskData.getUsers().get(pos));
                    }
                    if(pos==-1){
                        Log.d("TaskView", "An Error occurred finding the User: code -1");}
                }
                Bundle args = new Bundle();
                args.putParcelable("task", task);
                args.putParcelableArrayList("team", team);
                TaskInfoFragment taskInfoFragment = new TaskInfoFragment();
                taskInfoFragment.setArguments(args);
                taskInfoFragment.show(getFragmentManager(), "task_info_fragment");
            }
        });
    }
}
