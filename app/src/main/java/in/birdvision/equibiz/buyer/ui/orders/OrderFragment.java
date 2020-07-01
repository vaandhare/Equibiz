package in.birdvision.equibiz.buyer.ui.orders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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


public class OrderFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner Order_history_spinner;
    String userID, AuthToken, encryptedUserID;
    Equibiz_API_Interface equibiz_api_interface;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order, container, false);
        context = root.getContext();

        SharedPreferences mySharedPreferences = this.requireActivity().getSharedPreferences("FromLogin", Context.MODE_PRIVATE);
        AuthToken = mySharedPreferences.getString("LoginToken", "");

        equibiz_api_interface = EquibizApiService.getClient().create(Equibiz_API_Interface.class);

        userID = mySharedPreferences.getString("BuyerID", "");
//        userID = "mXLr0B3FETZHF0NJNhg0cksJhaQ9gB1w6zkeOpggdwI=";
//        userID = "/SEl1CTA8IauX/EmxsmRKW6zOFkNcbkzDN+GeekxEEo=";
//        userID = "q/VhbMT8BFuyAwd115v4Sr8gZCc5z5+ST75APzZYRBM=";

        getAllOrdersResponse(userID);

        Order_history_spinner = root.findViewById(R.id.spinner_order_history);
        Order_history_spinner.setOnItemSelectedListener(this);

        ArrayList<String> order_history_type = new ArrayList<>();
        order_history_type.add("All Orders");
        order_history_type.add("Orders Pending");
        order_history_type.add("Orders Confirmed");
        order_history_type.add("Orders Canceled");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, order_history_type);
        dataAdapter.setDropDownViewResource(R.layout.dropdown_menu);
        Order_history_spinner.setAdapter(dataAdapter);

        return root;
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
                    Toast.makeText(context, "Socket Time out. Please try again.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
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