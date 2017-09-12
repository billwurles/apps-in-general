package wburles.uk.taskview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Will on 16/02/2016.
 */
public class User implements Parcelable{
    private String id;
    private String user;

    public User(String user, String id){
        this.id = id;
        this.user = user;
    }

    protected User(Parcel in) {
        user = in.readString();
        id = in.readString();
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

    public String getID() {
        return id;
    }
    public String getUser() {
        return user;
    }

    @Override
    public String toString(){
        return id + "  ·  " + user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user);
        dest.writeString(id);
    }
}
