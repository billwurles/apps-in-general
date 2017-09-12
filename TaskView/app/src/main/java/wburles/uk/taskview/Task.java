package wburles.uk.taskview;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Will on 16/02/2016.
 */
public class Task implements Parcelable {
    String id;
    String name;
    String desc;
    GregorianCalendar cal;
    float hours;
    ArrayList<String> team;

    public Task(String id, String name, String desc, GregorianCalendar cal, float hours, ArrayList<String> team){
        this.id=id;
        this.name=name;
        this.desc=desc;
        this.cal = cal;
        this.hours=hours;
        this.team=team;
    }

    protected Task(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.desc = in.readString();
        this.hours = in.readFloat();
        this.cal = (GregorianCalendar) in.readSerializable();
        this.team = (ArrayList<String>) in.readSerializable();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setCalendar(cal);
        String s = String.format(sdf.format(cal.getTime()) + " · " + id + " · " + name + " · %.2f hrs", hours);
        return s;
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public GregorianCalendar getCal(){return cal;}
    public float getHours() {
        return hours;
    }
    public void setUserHours(){hours = hours / team.size();}
    public ArrayList<String> getTeam() {
        return team;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeFloat(hours);
        dest.writeSerializable(cal);
        dest.writeSerializable(team);
    }
}
