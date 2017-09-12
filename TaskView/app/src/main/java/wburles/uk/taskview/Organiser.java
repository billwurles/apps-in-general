package wburles.uk.taskview;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class Organiser {

    private ArrayList<Task> tasks;
    private ArrayList<User> users;

    public Organiser(ArrayList<Task> tasks, ArrayList<User> users){
        this.tasks = tasks;
        this.users = users;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void addUser(User newUser){
        this.users.add(0,newUser);
    }

    public int getUserPos(String id){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getID().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Task> findUserTasks(String id){
        ArrayList<Task> usersTasks = new ArrayList<Task>();
        for(Task task : tasks){
            for(String user : task.getTeam()){
                if(user.equals(id)){
                    usersTasks.add(task);
                }
            }
        }
        return usersTasks;
    }

    public boolean validTaskID(String id){
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getID().equals(id)){
                System.out.println("THEY TRIED TO DUPLICATE!");
                return false;
            }
        }
        System.out.println("No duplication");
        return true;
    }

    public boolean validUserID(String id){
        if(users.size()==0){
            return true;
        }
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getID().equals(id)){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public ArrayList<Task> setTasks(ArrayList<Task> newTasks){
        this.tasks = newTasks;
        return tasks;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public String toString(){
        return tasks.size() + " | " + users.size() + " | ";
    }


}
