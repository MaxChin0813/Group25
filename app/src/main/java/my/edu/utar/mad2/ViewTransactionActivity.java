package my.edu.utar.mad2;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ViewTransactionActivity extends AppCompatActivity {

    private ListView transactionListView;
    private TextView totalAmountTextView; // TextView to display total amount
    private DatabaseReference transactionsRef;
    private List<Transaction> transactionList;
    private TransactionListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        // Initialize views
        transactionListView = findViewById(R.id.transactionListView);
        totalAmountTextView = findViewById(R.id.totalAmountTextView);

        // Initialize Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        transactionsRef = database.getReference("transactions");

        // Initialize list and adapter
        transactionList = new ArrayList<>();
        adapter = new TransactionListAdapter(this, transactionList);
        transactionListView.setAdapter(adapter);

        // Fetch transaction data from Firebase
        fetchTransactionData();
    }

    private void fetchTransactionData() {
        transactionsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                transactionList.clear();
                double totalAmount = 0; // Variable to store total amount
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Transaction transaction = snapshot.getValue(Transaction.class);
                    transactionList.add(transaction);
                    // Add the amount of each transaction to the total
                    totalAmount += Double.parseDouble(transaction.getAmount());
                }
                adapter.notifyDataSetChanged();
                // Update total amount TextView
                totalAmountTextView.setText("Total Amount: RM" + String.format("%.2f", totalAmount));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
