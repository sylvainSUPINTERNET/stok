package com.example.stok.repositories


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stok.dto.CurrentStockPrice
import com.example.stok.services.IVantageService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VantageRepository(private val vantageService: IVantageService) {


    private val _currentStockPriceData = MutableLiveData<MutableList<CurrentStockPrice>>()
    val currentStockPriceData: LiveData<MutableList<CurrentStockPrice>> = _currentStockPriceData

    fun getStockCurrentPrice(symbol: List<String>, apiKey: String) {

        for ( i in symbol.indices ) {
            Log.d("STOCK", "getStockCurrentPrice: ${symbol[i]}")
            vantageService.getStockCurrentPrice(symbol[i], apiKey).enqueue(
                object: Callback<CurrentStockPrice> {
                    override fun onResponse(call: Call<CurrentStockPrice>, response: Response<CurrentStockPrice>) {
                        val currentStockPrice = response.body()
                        Log.d("STOCK-RESPONSE", "onResponse: ${currentStockPrice?.c}")
                        _currentStockPriceData.value = _currentStockPriceData.value ?: mutableListOf()
                        _currentStockPriceData.value?.add(currentStockPrice!!)
                        Log.d("STOCK-RESPONSE", "onResponse: ${_currentStockPriceData.value?.size}")
                        _currentStockPriceData.postValue(_currentStockPriceData.value!!)

                    }

                    override fun onFailure(call: Call<CurrentStockPrice>, t: Throwable) {
                        Log.d("ERROR-STOCK", "onFailure: ${t.message}")
                    }

                }



            )
        }

/*        symbol.map { symbol:String ->
            vantageService.getStockCurrentPrice(symbol, apiKey).enqueue(
                object: Callback<CurrentStockPrice> {

                    override fun onResponse(call: Call<CurrentStockPrice>, response: Response<CurrentStockPrice>) {
                        val currentStockPrice = response.body()
                        _currentStockPriceData.postValue(currentStockPrice!!)
                    }

                    override fun onFailure(call: Call<CurrentStockPrice>, t: Throwable) {
                        TODO("Not yet implemented")
                        Log.d("ERROR-STOCK", "onFailure: ${t.message}")
                    }

                }
            )
        }*/




    }
}