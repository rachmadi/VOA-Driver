package id.ac.mdp.voadriver.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marhadi Wijaya on 4/9/2017.
 */

public class User implements Parcelable {
    private String userId,userDomain,name,position,photo,phoneNumber,pin;
    private int currentQuota,maximunQuota;

    public User() {

    }

    public User(String userId, String userDomain, String name, String position, String photo, String phoneNumber, String pin, int currentQuota, int maximunQuota) {
        this.userId = userId;
        this.userDomain = userDomain;
        this.name = name;
        this.position = position;
        this.photo = photo;
        this.phoneNumber = phoneNumber;
        this.pin = pin;
        this.currentQuota = currentQuota;
        this.maximunQuota = maximunQuota;
    }

    protected User(Parcel in) {
        userId = in.readString();
        userDomain = in.readString();
        name = in.readString();
        position = in.readString();
        photo = in.readString();
        phoneNumber = in.readString();
        pin = in.readString();
        currentQuota = in.readInt();
        maximunQuota = in.readInt();
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getCurrentQuota() {
        return currentQuota;
    }

    public void setCurrentQuota(int currentQuota) {
        this.currentQuota = currentQuota;
    }

    public int getMaximunQuota() {
        return maximunQuota;
    }

    public void setMaximunQuota(int maximunQuota) {
        this.maximunQuota = maximunQuota;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(userDomain);
        dest.writeString(name);
        dest.writeString(position);
        dest.writeString(photo);
        dest.writeString(phoneNumber);
        dest.writeString(pin);
        dest.writeInt(currentQuota);
        dest.writeInt(maximunQuota);
    }
}
