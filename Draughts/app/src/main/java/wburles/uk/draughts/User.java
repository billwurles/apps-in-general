package wburles.uk.draughts;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String desc;
    private String user;
    private int points;

    public User(String user, String desc, int points){
        this.user = user;
        this.desc = desc;
        this.points = points;
    }

    protected User(Parcel in) {
        user = in.readString();
        desc = in.readString();
        points = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getDesc() {
        return desc;
    }
    public String getUser() {
        return user;
    }
    public void addPoint(){
        points++;
    }
    public int getPoints(){return points;}
    @Override
    public String toString(){
        return points + " · " + user + "  ·  " + desc;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user);
        dest.writeString(desc);
        dest.writeInt(points);
    }
}
