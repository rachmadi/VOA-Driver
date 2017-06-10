package id.ac.mdp.voadriver.models;

/**
 * Created by Marhadi Wijaya on 5/9/2017.
 */

public class PettyCash {
    private String petty_cash_id, driver_id, timestamp, status;
    private double amount;

    public PettyCash() {
    }

    public PettyCash(String petty_cash_id, String driver_id, String timestamp, String status, double amount) {
        this.petty_cash_id = petty_cash_id;
        this.driver_id = driver_id;
        this.timestamp = timestamp;
        this.status = status;
        this.amount = amount;
    }

    public String getPetty_cash_id() {
        return petty_cash_id;
    }

    public void setPetty_cash_id(String petty_cash_id) {
        this.petty_cash_id = petty_cash_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
