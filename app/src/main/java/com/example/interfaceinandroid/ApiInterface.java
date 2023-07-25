package com.example.interfaceinandroid;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("api/banking/{user_id}")
    Call<BankDetailsResponseModel>getNumberOfAccounts(
            @Header("Authorization") String token,
            @Path("user_id")int user_id
    );

    @DELETE("api/banking/{id}")
    Call<deleteAccountModel>deleteBankAccount(
            @Header("Authorization") String token,
            @Path("id")int id
    );
}
