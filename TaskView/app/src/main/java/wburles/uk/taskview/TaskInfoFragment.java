package wburles.uk.taskview;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class TaskInfoFragment extends DialogFragment {
    Task task;
    ArrayList<String> infoArray = new ArrayList<String>();
    ArrayAdapter<String> infoAdapter;
    ArrayList<User> teamArray;
    ArrayAdapter<User> userAdapter;

    ListView infoList;
    ListView teamList;


    public TaskInfoFragment() {}

    @Override
    public void setArguments(Bundle args) {
        task = (Task) args.get("task");
        teamArray = args.getParcelableArrayList("team");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_info, container);
        getDialog().setTitle(task.getName());

        infoList = (ListView) view.findViewById(R.id.task_info_list);
        teamList = (ListView) view.findViewById(R.id.list_task_team);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        infoArray.add(sdf.format(task.getCal().getTime())+"   "+"ID: "+task.getID().toUpperCase());
        infoArray.add("Description: "+task.getDesc());
        infoArray.add(task.getHours() + " hours");
        infoAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,infoArray);
        infoList.setAdapter(infoAdapter);
        infoAdapter.notifyDataSetChanged();

        userAdapter = new ArrayAdapter<User>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,teamArray);
        teamList.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
        return view;
    }
}
