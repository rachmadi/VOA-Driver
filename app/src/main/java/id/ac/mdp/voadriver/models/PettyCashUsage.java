package id.ac.mdp.voadriver.models;

/**
 * Created by Marhadi Wijaya on 5/9/2017.
 */

public class PettyCashUsage {
    private String petty_cash_usage_id, petty_cash_id, timestamp, description;
    private double amount;

    public PettyCashUsage() {
    }

    public PettyCashUsage(String petty_cash_usage_id, String petty_cash_id, String timestamp, String description, double amount) {
        this.petty_cash_usage_id = petty_cash_usage_id;
        this.petty_cash_id = petty_cash_id;
        this.timestamp = timestamp;
        this.description = description;
        this.amount = amount;
    }

    public String getPetty_cash_usage_id() {
        return petty_cash_usage_id;
    }

    public void setPetty_cash_usage_id(String petty_cash_usage_id) {
        this.petty_cash_usage_id = petty_cash_usage_id;
    }

    public String getPetty_cash_id() {
        return petty_cash_id;
    }

    public void setPetty_cash_id(String petty_cash_id) {
        this.petty_cash_id = petty_cash_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
