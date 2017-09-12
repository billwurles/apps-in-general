package wburles.uk.taskview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

public class TaskViewActivity extends AppCompatActivity {
    private ArrayList<Task> tasksArray = new ArrayList<Task>();
    private ArrayList<User> usersArray = new ArrayList<User>();
    private Organiser taskData = new Organiser(tasksArray,usersArray);

    private ListView taskList;
    private ArrayAdapter<Task> taskAdapter;

    private final static int NEW_TASK_REQUEST=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("TaskView","TaskView Created");
        demonstrationMethod(); // delete this to use real-world app
        taskList = (ListView) findViewById(R.id.task_list);

        tasksArray = taskData.getTasks();
        taskAdapter = new ArrayAdapter<Task>(this,android.R.layout.simple_list_item_1, tasksArray);
        taskList.setAdapter(taskAdapter);
        sort();
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = taskData.getTasks().get(position);
                ArrayList<User> team = new ArrayList<User>();
                for(String userID : task.getTeam()){
                    int pos = taskData.getUserPos(userID);
                    if(pos>=0){
                        team.add(taskData.getUsers().get(pos));
                    }
                    if(pos==-1){Log.d("TaskView", "An Error occurred finding the User: code -1");}
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == NEW_TASK_REQUEST){
            if(resultCode == RESULT_OK) {
                taskData.addTask((Task) data.getExtras().get("newTask"));
                Task newTask = (Task) data.getExtras().get("newTask");
                Log.d("TaskView","New task recieved:"+ newTask.toString());
                sort();
            }
        }
    }

    public void retrieveNewUser(User newUser){
        Log.d("TaskView", "retrieveNewUser: " + newUser.toString());
        taskData.addUser(newUser);
        usersArray = taskData.getUsers();
    }

    private void sort(){
        Collections.sort(taskData.setTasks(taskData.getTasks()), new DateSorter());
        tasksArray = taskData.getTasks();
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tasks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_task){
            Log.d("TaskView","Activity: New Task");
            Intent intent = new Intent(getApplicationContext(), NewTaskActivity.class);
            intent.putParcelableArrayListExtra("tasks", taskData.getTasks());
            intent.putParcelableArrayListExtra("users", taskData.getUsers());
            startActivityForResult(intent, NEW_TASK_REQUEST);
        }
        if (id == R.id.menu_users) {
            Log.d("TaskView","Fragment: View Users");
            Bundle args = new Bundle();
            args.putInt("arg",2);
            args.putParcelableArrayList("users", taskData.getUsers());
            args.putParcelableArrayList("tasks", taskData.getTasks());
            ViewUsersFragment viewUsersFragment = new ViewUsersFragment();
            viewUsersFragment.setArguments(args);
            viewUsersFragment.show(getFragmentManager(), "view_users_fragment");

        }
        if (id == R.id.menu_new_user){
            Log.d("TaskView","Fragment: New User");
            NewUserFragment newUserFragment = new NewUserFragment();
            Bundle args = new Bundle();
            args.putParcelableArrayList("users", taskData.getUsers());
            args.putParcelableArrayList("tasks", taskData.getTasks());
            newUserFragment.setArguments(args);
            newUserFragment.show(getFragmentManager(), "new_user_fragment");
        }

        return super.onOptionsItemSelected(item);
    }

    public void demonstrationMethod(){ // creates users and tasks for demonstration purposes
        String userNames[] = {"Ethan","Fiona","Charlie","Emma","Chris"};
        String userIDs[] = {"U1","U2","U3","U4","U5"};
        for(int i=0;i<=4;i++) {
            User tmpUser = new User(userNames[i], userIDs[i]);
            taskData.addUser(tmpUser);
        }
        String taskName[] = {"Go Shopping","Finish Coursework","Watch ET","Pub!","Get Mum Present","Buy New Tyres"};
        String taskIDs[] = {"T1","T2","T3","T4","T5","T6"};
        String taskDesc[] = {"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc finibus massa et felis iaculis tempus.",
                "Biscuit bonbon jelly-o ice cream macaroon. Jelly beans dragée jujubes. Candy gummi bears macaroon jujubes tiramisu chocolate.",
                "Lorizzle ipsum dolizzle sit owned, consectetizzle adipiscing elit. Its fo rizzle sapien velizzle, yippiyo volutpizzle",
                "Bacon ipsum dolor amet aliqua salami sunt velit ullamco ipsum ea brisket cupim laboris laborum short ribs veniam tri-tip",
                "Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis",
                "Thundercats before they sold out ugh you probably haven't heard of them blue bottle 3 wolf moon"};

        GregorianCalendar date[] = new GregorianCalendar[6];
        GregorianCalendar cal1 = new GregorianCalendar(); cal1.set(2016,3,23); date[0] = cal1;
        GregorianCalendar cal2 = new GregorianCalendar(); cal2.set(2016,5,11); date[1] = cal2;
        GregorianCalendar cal3 = new GregorianCalendar(); cal3.set(2016,8,29); date[2] = cal3;
        GregorianCalendar cal4 = new GregorianCalendar(); cal4.set(2016,4,1);  date[3] = cal4;
        GregorianCalendar cal5 = new GregorianCalendar(); cal5.set(2017,0,1);  date[4] = cal5;
        GregorianCalendar cal6 = new GregorianCalendar(); cal6.set(2016,4,19); date[5] = cal6;

        java.util.Random rand = new java.util.Random();

        ArrayList<String> team[] = new ArrayList[6];
        for(int i = 0; i<6; i++){
            team[i] = new ArrayList<String>();
            int count = rand.nextInt(4)+2;
            while(count != 0){
                count--;
                String tmpID = taskData.getUsers().get(rand.nextInt(5)).getID();
                boolean valid = true;
                if(!team[i].isEmpty()){
                    for(String user : team[i]){
                        if(user.equals(tmpID)) {
                            valid = false;
                            count++;
                        }   }   }
                if(valid){
                    team[i].add(tmpID);
                }
            }
        }
        for(int i=0;i<=5;i++) {
            Task tmpTask = new Task(taskIDs[i],taskName[i],taskDesc[i],date[i],rand.nextInt(30)+1,team[i]);
            taskData.addTask(tmpTask);
        }
    }
}
