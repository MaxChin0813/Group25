package my.edu.utar.mad2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String amount;
    private String category;
    private String notes;
    private String date;

    // Default constructor (required for Firebase)
    public Transaction() {
    }

    public Transaction(String amount, String category, String notes, String date) {
        this.amount = amount;
        this.category = category;
        this.notes = notes;
        this.date = date;
    }

    // Getters and setters
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Helper method to get current date in "yyyy-MM-dd" format
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
