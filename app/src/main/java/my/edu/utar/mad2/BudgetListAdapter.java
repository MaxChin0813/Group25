package my.edu.utar.mad2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class BudgetListAdapter extends ArrayAdapter<Budget> {

    private Context context;
    private List<Budget> budgets;

    public BudgetListAdapter(Context context, List<Budget> budgets) {
        super(context, 0, budgets);
        this.context = context;
        this.budgets = budgets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.budget_item, parent, false);
        }

        Budget currentBudget = budgets.get(position);

        TextView dateTextView = listItem.findViewById(R.id.dateTextView);
        dateTextView.setText("Date: " + currentBudget.getDate());

        TextView typeTextView = listItem.findViewById(R.id.typeTextView);
        typeTextView.setText("Type: " + currentBudget.getType());

        TextView noteTextView = listItem.findViewById(R.id.noteTextView);
        noteTextView.setText("Note: " + currentBudget.getNotes());

        TextView amountTextView = listItem.findViewById(R.id.amountTextView);
        amountTextView.setText("Amount: RM " + currentBudget.getAmount());

        return listItem;
    }
}
