package in.birdvision.equibiz.orders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import in.birdvision.equibiz.R;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner Order_history_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Order_history_spinner = findViewById(R.id.spinner_order_history);
        Order_history_spinner.setOnItemSelectedListener(this);

        ArrayList<String> order_history_type = new ArrayList<>();
        order_history_type.add("All Orders");
        order_history_type.add("Orders Pending");
        order_history_type.add("Orders Confirmed");
        order_history_type.add("Orders Canceled");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, order_history_type);
        dataAdapter.setDropDownViewResource(R.layout.dropdown_menu);
        Order_history_spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
