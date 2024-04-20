package my.edu.utar.mad2;

import static my.edu.utar.mad2.Transaction.getCurrentDate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertBudgetActivity extends AppCompatActivity {

    private DatabaseReference budgetsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_budget);

        // Initialize Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        budgetsRef = database.getReference("budgets");

        // Add Button to view budget
        Button viewBudgetButton = findViewById(R.id.viewBudgetButton);
        viewBudgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to view budget page
                Intent intent = new Intent(InsertBudgetActivity.this, ViewBudgetActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showInsertBudgetDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insert Budget");

        // Inflate the dialog layout
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_insert_budget, null);
        builder.setView(dialogView);

        final EditText amountEditText = dialogView.findViewById(R.id.amountEditText);
        final EditText typeEditText = dialogView.findViewById(R.id.typeEditText);
        final EditText notesEditText = dialogView.findViewById(R.id.notesEditText);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String amount = amountEditText.getText().toString().trim();
                String type = typeEditText.getText().toString().trim();
                String notes = notesEditText.getText().toString().trim();
                String date = getCurrentDate(); // Get the current date

                if (amount.isEmpty() || type.isEmpty()) {
                    Toast.makeText(InsertBudgetActivity.this, "Please enter amount and type", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create Budget object with current date
                Budget budget = new Budget(amount, type, notes);
                budget.setDate(date); // Set the date field of the Budget object

                // Generate unique key for the budget
                String budgetId = budgetsRef.push().getKey();

                // Insert the budget into the database under the generated key
                if (budgetId != null) {
                    budgetsRef.child(budgetId).setValue(budget);
                    Toast.makeText(InsertBudgetActivity.this, "Budget saved successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, you can clear the EditText fields here
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
