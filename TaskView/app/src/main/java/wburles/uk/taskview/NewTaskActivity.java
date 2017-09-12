package wburles.uk.taskview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class NewTaskActivity extends AppCompatActivity {
    private Organiser taskData;
    private ArrayList<Task> tasksArray;
    private ArrayList<User> usersArray;
    private final ArrayList<String> team = new ArrayList<String>();

    private Intent intent;

    private EditText name;
    private EditText desc;
    private EditText id;
    private DatePicker date;
    private NumberPicker hours;
    private Button teamButton;
    private Button viewTeamButton;
    private Button finish;

    public void retrieveTeamMember(String id){
        Log.d("TaskView", "retrieveTeamMember running!! - " + id);
        boolean valid = true;
        for(String teamID : team){
            if(teamID.equals(id)){
                valid = false;
            }
        }
        if(valid){
            team.add(id);
        } else {
            Toast.makeText(getApplication().getApplicationContext(),"That team member is already added",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        this.setTitle(R.string.new_task_title);
        intent = getIntent();

        tasksArray = intent.getParcelableArrayListExtra("tasks");
        usersArray = intent.getParcelableArrayListExtra("users");
        taskData = new Organiser(tasksArray,usersArray);

        Log.d("TaskView","NewTaskActivity: array sizes:" + taskData.toString());

        name = (EditText) findViewById(R.id.task_name);
        desc = (EditText) findViewById(R.id.task_desc);
        id = (EditText) findViewById(R.id.task_id);
        date = (DatePicker) findViewById(R.id.date_picker);
        hours = (NumberPicker) findViewById(R.id.hours_picker);
        hours.setMinValue(1);
        hours.setMaxValue(9999);
        teamButton = (Button) findViewById(R.id.choose_team_button);
        viewTeamButton = (Button) findViewById(R.id.view_team_button);
        finish = (Button) findViewById(R.id.finish_button);

        teamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt("arg",1);
                args.putParcelableArrayList("users", taskData.getUsers());
                args.putParcelableArrayList("tasks", taskData.getTasks());

                ViewUsersFragment viewUsers = new ViewUsersFragment();
                viewUsers.setArguments(args);
                viewUsers.show(getFragmentManager(), "new_user_fragment");
            }
        });

        viewTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!team.isEmpty()) {
                    Bundle args = new Bundle();
                    args.putInt("arg", 3);
                    ArrayList<User> chosenUsers = new ArrayList<User>();
                    for (String userID : team) {
                        int pos = taskData.getUserPos(userID);
                        if (pos >= 0) {
                            chosenUsers.add(taskData.getUsers().get(pos));
                        }
                        if (pos == -1) {
                            Log.d("TaskView", "An Error occurred finding the User: code -1");
                        }
                    }
                    args.putParcelableArrayList("users", chosenUsers);
                    args.putParcelableArrayList("tasks", taskData.getTasks());

                    ViewUsersFragment viewUsers = new ViewUsersFragment();
                    viewUsers.setArguments(args);
                    viewUsers.show(getFragmentManager(), "new_user_fragment");
                } else {
                    Toast.makeText(getApplicationContext(), "You must add users first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!taskData.validTaskID(String.valueOf(id.getText()))){
                    Toast.makeText(getApplicationContext(),"That ID is already in use",Toast.LENGTH_LONG).show();
                    System.out.println("Attempted to use duplicate ID");
                }
                else if(name.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Name field is empty",Toast.LENGTH_LONG).show();
                    System.out.println("Attempted to enter empty name");
                }
                else if(desc.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Description field is empty",Toast.LENGTH_LONG).show();
                    System.out.println("Attempted to enter empty description");
                }
                else if(id.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"ID field is empty",Toast.LENGTH_LONG).show();
                    System.out.println("Attempted to enter empty ID");
                }
                else if(team.size()<1){
                    Toast.makeText(getApplicationContext(),"There are no team members",Toast.LENGTH_LONG).show();
                    System.out.println("Attempted to enter empty team");
                }
                else{
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.setTimeInMillis(date.getCalendarView().getDate());

                    Task newTask = new Task(String.valueOf(id.getText()),String.valueOf(name.getText()),
                            String.valueOf(desc.getText()),cal,hours.getValue(),team);

                    Log.d("TaskView", "NewTaskActivity: New Task: "+newTask.toString());
                    Intent result = new Intent();
                    result.putExtra("newTask",newTask);
                    setResult(RESULT_OK, result);
                    finish();
                }
            }
        });
    }
}
