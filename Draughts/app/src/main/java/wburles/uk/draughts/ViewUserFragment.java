package wburles.uk.draughts;

import android.app.DialogFragment;
import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import wburles.uk.draughts.MySQL.GameDAO;

public class ViewUserFragment extends DialogFragment {
    GameDAO userDB;
    ArrayList<User> usersArray;
    ListView userList;
    UserAdapter userAdapter;

    int flag;
    User selectedUser;

    public ViewUserFragment(){}

    @Override
    public void setArguments(Bundle args){
        flag = args.getInt("flag");
        if(flag == 1){
            selectedUser = args.getParcelable("user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_user, container);

        if(flag == 0){
            getDialog().setTitle("Select Player One");
        } else if(flag == 1) {
            getDialog().setTitle("Select Player Two");
        }


        userDB = new GameDAO();
        usersArray = userDB.getUsers(getActivity().getApplicationContext());

        userList = (ListView) view.findViewById(R.id.frag_user_list);
        userAdapter = new UserAdapter(getActivity().getApplicationContext(), usersArray);
        userList.setAdapter(userAdapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flag == 0) { // if the first user selected
                    ((MenuActivty) getActivity()).getUser(usersArray.get(position), flag);
                    Toast.makeText(getActivity().getApplicationContext(),
                            usersArray.get(position).getUser() + " Selected", Toast.LENGTH_SHORT).show();
                    dismiss();
                } else if (flag == 1) { // if the second user selected
                    if (usersArray.get(position).getUser().equals(selectedUser.getUser())) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "User is already selected", Toast.LENGTH_SHORT).show();
                    } else {
                        ((MenuActivty) getActivity()).getUser(usersArray.get(position), flag);
                        Toast.makeText(getActivity().getApplicationContext(),
                                usersArray.get(position).getUser() + " Selected", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                }
            }
        });

        return view;
    }
}
