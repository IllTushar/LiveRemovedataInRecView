package com.example.interfaceinandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterClass.deleteApiCall{
RecyclerView recyclerView;
SharedPreference sharedPreference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recView);
        sharedPreference =new SharedPreference(this);
        callBankApi();
    }

    private void callBankApi()
    {
        ProgressDialog progressDialog = new ProgressDialog(this);
        // progressDialog.show();
        progressDialog.setMessage("Please wait...");
        String Token  = "1153|x55oMihP57qoNgEvvRzogomC6SHJDho1TJkoFo1h";
       // int userId  = Integer.parseInt(sharedPreference.getData("user_id"));
        ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        Call<BankDetailsResponseModel> call = apiInterface.getNumberOfAccounts("Bearer "+Token,424);
        call.enqueue(new Callback<BankDetailsResponseModel>() {
            @Override
            public void onResponse(Call<BankDetailsResponseModel> call, Response<BankDetailsResponseModel> response) {
                if (response.code()==200){
                    List<BankDetailsResponseModel.Datum> list = response.body().getData();
                    AdapterClass adapter = new AdapterClass(list,getApplicationContext(),MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    //   progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<BankDetailsResponseModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void deleteApi() {
        ProgressDialog pd= new ProgressDialog(this);
        pd.show();
        pd.setMessage("Please wait...");
        String Token ="1153|x55oMihP57qoNgEvvRzogomC6SHJDho1TJkoFo1h";
        int accountID = Integer.parseInt(sharedPreference.getData("id"));
        ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        Call<deleteAccountModel>call = apiInterface.deleteBankAccount("Bearer "+Token,accountID);
        call.enqueue(new Callback<deleteAccountModel>() {
            @Override
            public void onResponse(Call<deleteAccountModel> call, Response<deleteAccountModel> response) {
                if (response.code()==200){
                    Toast.makeText(MainActivity.this, "Account has been deleted..", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    callBankApi();
                }
            }

            @Override
            public void onFailure(Call<deleteAccountModel> call, Throwable t) {
                pd.dismiss();
            }
        });
    }
}