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

public class ViewBudgetActivity extends AppCompatActivity {

    private ListView budgetListView;
    private DatabaseReference budgetsRef;
    private List<Budget> budgetList;
    private BudgetListAdapter adapter;
    private TextView totalAmountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budget);

        // Initialize views
        budgetListView = findViewById(R.id.budgetListView);
        totalAmountTextView = findViewById(R.id.totalAmountTextView);

        // Initialize Firebase database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        budgetsRef = database.getReference("budgets");

        // Initialize list and adapter
        budgetList = new ArrayList<>();
        adapter = new BudgetListAdapter(this, budgetList);
        budgetListView.setAdapter(adapter);

        // Fetch budget data from Firebase
        fetchBudgetData();
    }

    private void fetchBudgetData() {
        budgetsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                budgetList.clear();
                double totalAmount = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Budget budget = snapshot.getValue(Budget.class);
                    budgetList.add(budget);
                    // Calculate total amount
                    totalAmount += Double.parseDouble(budget.getAmount());
                }
                // Update total amount TextView
                totalAmountTextView.setText("Total Amount: RM" + String.format("%.2f", totalAmount));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
