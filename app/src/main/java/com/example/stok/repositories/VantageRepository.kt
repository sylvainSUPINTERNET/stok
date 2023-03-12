package com.example.stok.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stok.dto.CurrentStockPrice
import com.example.stok.services.IVantageService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VantageRepository(private val vantageService: IVantageService) {
    private val _currentStockPriceData = MutableLiveData<CurrentStockPrice>()
    val currentStockPriceData: LiveData<CurrentStockPrice> = _currentStockPriceData

    fun getStockCurrentPrice(symbol: String, apiKey: String) {
        vantageService.getStockCurrentPrice(symbol, apiKey).enqueue(
            object: Callback<CurrentStockPrice> {

                override fun onResponse(call: Call<CurrentStockPrice>, response: Response<CurrentStockPrice>) {
                    val currentStockPrice = response.body()
                    _currentStockPriceData.postValue(currentStockPrice!!)
                }

                override fun onFailure(call: Call<CurrentStockPrice>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
}