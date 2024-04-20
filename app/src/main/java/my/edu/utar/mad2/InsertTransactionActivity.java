package my.edu.utar.mad2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertTransactionActivity extends AppCompatActivity {

    private DatabaseReference transactionsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_transaction); // Set correct layout here

        // Initialize Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        transactionsRef = database.getReference("transactions");

        // Set click listener for the add transaction button
        findViewById(R.id.addTransactionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTransactionDialog();
            }
        });
    }

    private void showAddTransactionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Transaction");

        // Inflate the dialog layout
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_transaction, null);
        builder.setView(view);

        final EditText amountEditText = view.findViewById(R.id.amountEditText);
        final EditText categoryEditText = view.findViewById(R.id.categoryEditText);
        final EditText notesEditText = view.findViewById(R.id.notesEditText);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String amount = amountEditText.getText().toString().trim();
                String category = categoryEditText.getText().toString().trim();
                String notes = notesEditText.getText().toString().trim();
                String date = getCurrentDate(); // Get current date in "yyyy-MM-dd" format

                // Get a unique key for the transaction
                String transactionId = transactionsRef.push().getKey();

                // Create a Transaction object
                Transaction transaction = new Transaction(amount, category, notes, date);

                // Insert the transaction into the database under the generated key
                if (transactionId != null) {
                    transactionsRef.child(transactionId).setValue(transaction);
                    // Optionally, you can also add success message or navigate to another screen
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

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public void viewTransactions(View view) {
        Intent intent = new Intent(this, ViewTransactionActivity.class);
        startActivity(intent);
    }
}
