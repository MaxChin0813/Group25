package my.edu.utar.mad2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeSimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_simple);

        // Set click listeners for buttons
        ImageButton budgetButton = findViewById(R.id.budgetButton);
        ImageButton transactionButton = findViewById(R.id.transactionButton);

        budgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open InsertBudgetActivity
                startActivity(new Intent(HomeSimpleActivity.this, InsertBudgetActivity.class));
            }
        });

        transactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open InsertTransactionActivity
                startActivity(new Intent(HomeSimpleActivity.this, InsertTransactionActivity.class));
            }
        });
    }
}
