package in.birdvision.equibiz.buyer.orders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import in.birdvision.equibiz.API.equibizAPI.EquibizApiService;
import in.birdvision.equibiz.API.equibizAPI.Equibiz_API_Interface;
import in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders.AllOrdersResponse;
import in.birdvision.equibiz.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner Order_history_spinner;
    String userID, AuthToken, encryptedUserID;
    Equibiz_API_Interface equibiz_api_interface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        SharedPreferences mySharedPreferences = this.getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "xxxxx");

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        userID = mySharedPreferences.getString("BuyerID", "xxxxx");
//        userID = "mXLr0B3FETZHF0NJNhg0cksJhaQ9gB1w6zkeOpggdwI=";
//        userID = "/SEl1CTA8IauX/EmxsmRKW6zOFkNcbkzDN+GeekxEEo=";
//        userID = "q/VhbMT8BFuyAwd115v4Sr8gZCc5z5+ST75APzZYRBM=";

        getAllOrdersResponse(userID);

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

    private void getAllOrdersResponse(String userID) {
        final AllOrdersResponse allOrdersResponse = new AllOrdersResponse(userID);
        Call<AllOrdersResponse> responseCall = equibiz_api_interface.allOrderResponse(allOrdersResponse, "Bearer " + AuthToken);

        responseCall.enqueue(new Callback<AllOrdersResponse>() {
            @Override
            public void onResponse(@NotNull Call<AllOrdersResponse> call, @NotNull Response<AllOrdersResponse> response) {
                AllOrdersResponse response1 = response.body();

            }

            @Override
            public void onFailure(@NotNull Call<AllOrdersResponse> call, @NotNull Throwable t) {
                if (t instanceof SocketTimeoutException)
                    Toast.makeText(OrderActivity.this, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(OrderActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
