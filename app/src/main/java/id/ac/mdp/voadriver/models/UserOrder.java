package id.ac.mdp.voadriver.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marhadi Wijaya on 4/9/2017.
 */

public class UserOrder implements Parcelable{
    private String order_id;
    private String driver_id;
    private String user_id;
    private String booking_order;
    private String start_order;
    private String finish_order;
    private String origin_location;
    private String destination_location;
    private String order_type;
    private String order_status;

    private String kegiatan;

    private double total_distance, cost_estimation, real_cost;
    UserOrder() {
    }

    public UserOrder(String order_id, String driver_id, String user_id, String booking_order, String start_order, String finish_order, String origin_location, String destination_location, double total_distance, double cost_estimation, double real_cost, String order_type, String order_status, String kegiatan) {
        this.order_id = order_id;
        this.driver_id = driver_id;
        this.user_id = user_id;
        this.origin_location = origin_location;
        this.destination_location = destination_location;
        this.booking_order = booking_order;
        this.start_order = start_order;
        this.finish_order = finish_order;
        this.total_distance = total_distance;
        this.cost_estimation = cost_estimation;
        this.real_cost = real_cost;
        this.order_type = order_type;
        this.order_status = order_status;
        this.kegiatan=kegiatan;
    }

    private UserOrder(Parcel in) {
        order_id = in.readString();
        driver_id = in.readString();
        user_id = in.readString();
        booking_order = in.readString();
        start_order = in.readString();
        finish_order = in.readString();
        origin_location = in.readString();
        destination_location = in.readString();
        order_type = in.readString();
        order_status = in.readString();
        total_distance = in.readDouble();
        cost_estimation = in.readDouble();
        real_cost = in.readDouble();
        kegiatan = in.readString();
    }

    public static final Creator<UserOrder> CREATOR = new Creator<UserOrder>() {
        @Override
        public UserOrder createFromParcel(Parcel in) {
            return new UserOrder(in);
        }

        @Override
        public UserOrder[] newArray(int size) {
            return new UserOrder[size];
        }
    };

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrigin_location() {
        return origin_location;
    }

    public void setOrigin_location(String origin_location) {
        this.origin_location = origin_location;
    }

    public String getDestination_location() {
        return destination_location;
    }

    public void setDestination_location(String destination_location) {
        this.destination_location = destination_location;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getBooking_order() {
        return booking_order;
    }

    public void setBooking_order(String booking_order) {
        this.booking_order = booking_order;
    }

    public String getStart_order() {
        return start_order;
    }

    public void setStart_order(String start_order) {
        this.start_order = start_order;
    }

    public String getFinish_order() {
        return finish_order;
    }

    public void setFinish_order(String finish_order) {
        this.finish_order = finish_order;
    }

    public double getTotal_distance() {
        return total_distance;
    }

    public void setTotal_distance(double total_distance) {
        this.total_distance = total_distance;
    }

    public double getCost_estimation() {
        return cost_estimation;
    }

    public void setCost_estimation(double cost_estimation) {
        this.cost_estimation = cost_estimation;
    }

    public double getReal_cost() {
        return real_cost;
    }

    public void setReal_cost(double real_cost) {
        this.real_cost = real_cost;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(order_id);
        dest.writeString(driver_id);
        dest.writeString(user_id);
        dest.writeString(booking_order);
        dest.writeString(start_order);
        dest.writeString(finish_order);
        dest.writeString(origin_location);
        dest.writeString(destination_location);
        dest.writeString(order_type);
        dest.writeString(order_status);
        dest.writeDouble(total_distance);
        dest.writeDouble(cost_estimation);
        dest.writeDouble(real_cost);
        dest.writeString(kegiatan);
    }
}
