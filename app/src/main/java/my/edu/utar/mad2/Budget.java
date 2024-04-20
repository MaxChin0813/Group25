package my.edu.utar.mad2;

public class Budget {
    private String amount;
    private String type;
    private String notes;
    private String date; // Assuming you want to store the date of the budget

    public Budget() {
        // Default constructor required for Firebase
    }

    public Budget(String amount, String type, String notes) {
        this.amount = amount;
        this.type = type;
        this.notes = notes;
    }

    // Getters and setters for all fields

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
