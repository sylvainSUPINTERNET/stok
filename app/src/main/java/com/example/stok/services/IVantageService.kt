package com.example.stok.services

import com.example.stok.dto.CurrentStockPrice
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IVantageService {

    // ?symbol={symbol}&token={apiKey}
    @GET("quote")
    fun getStockCurrentPrice(
        @Query("symbol") symbol: String, // 1 symbol = 1 call API so even we batch, count as "normal" call ...
        @Query("token") apiKey: String
    ): Call<CurrentStockPrice>

}