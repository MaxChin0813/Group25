package my.edu.utar.mad2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class TransactionListAdapter extends ArrayAdapter<Transaction> {

    private Context context;
    private List<Transaction> transactions;

    public TransactionListAdapter(Context context, List<Transaction> transactions) {
        super(context, 0, transactions);
        this.context = context;
        this.transactions = transactions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.transaction_item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.dateTextView = convertView.findViewById(R.id.dateTextView);
            viewHolder.typeTextView = convertView.findViewById(R.id.typeTextView);
            viewHolder.noteTextView = convertView.findViewById(R.id.noteTextView);
            viewHolder.amountTextView = convertView.findViewById(R.id.amountTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Transaction currentTransaction = transactions.get(position);

        // Set data to TextViews
        viewHolder.dateTextView.setText("Date: " + currentTransaction.getDate());
        viewHolder.typeTextView.setText("Type: " + currentTransaction.getCategory());
        viewHolder.noteTextView.setText("Note: " + currentTransaction.getNotes());
        viewHolder.amountTextView.setText("Amount: RM " + currentTransaction.getAmount());

        return convertView;
    }

    // ViewHolder pattern for efficient ListView scrolling
    static class ViewHolder {
        TextView dateTextView;
        TextView typeTextView;
        TextView noteTextView;
        TextView amountTextView;
    }
}
