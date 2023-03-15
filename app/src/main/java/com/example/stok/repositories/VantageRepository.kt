package com.example.stok.repositories


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stok.dto.CurrentStockPrice
import com.example.stok.services.IVantageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VantageRepository(private val vantageService: IVantageService) {


    private val _currentStockPriceData = MutableLiveData<MutableList<CurrentStockPrice>>()
    val currentStockPriceData: LiveData<MutableList<CurrentStockPrice>> = _currentStockPriceData

    fun getStockCurrentPrice ( symbol: List<String> , apiKey: String ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val currentStockPriceList:MutableList<CurrentStockPrice> = mutableListOf()

                for ( i in symbol.indices ) {
                    Log.d("RESPONSE-STOCK", "getStockCurrentPrice: ${symbol[i]}")
                    val currentStockPrice = vantageService.getStockCurrentPrice(symbol[i], apiKey).execute().body()

                    currentStockPriceList.add(currentStockPrice!!)
                }


                Log.d("RESPONSE-STOCK", currentStockPriceList.toString())

                // postValue is for background thread !
                // setValue is for main thread !
/*
                _currentStockPriceData.postValue(currentStockPriceList)
*/
                withContext(Dispatchers.Main) {
                    _currentStockPriceData.value = currentStockPriceList
                }


            } catch ( e: Exception ) {
                Log.d("ERROR-STOCK", "onFailure: ${e.message}")
            }
        }
    }

    /*fun getStockCurrentPrice(symbol: List<String>, apiKey: String) {

        for ( i in symbol.indices ) {
            Log.d("STOCK", "getStockCurrentPrice: ${symbol[i]}")
            vantageService.getStockCurrentPrice(symbol[i], apiKey).enqueue(
                object: Callback<CurrentStockPrice> {
                    override fun onResponse(call: Call<CurrentStockPrice>, response: Response<CurrentStockPrice>) {
                        val currentStockPrice = response.body()

                        // TODO call for


                        Log.d("STOCK-RESPONSE", "onResponse: ${currentStockPrice?.c} : ${symbol[i]}")
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



    }*/
}