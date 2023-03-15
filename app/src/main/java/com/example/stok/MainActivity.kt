package com.example.stok


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.stok.databinding.ActivityMainBinding
import com.example.stok.repositories.VantageRepository
import com.example.stok.services.IVantageService
import com.example.stok.ui.symbol.SymbolItem
import com.example.stok.ui.symbol.SymbolRecycleViewAdapter
import com.example.stok.utils.WifiChecker
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = SymbolRecycleViewAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        FirebaseAuth.getInstance().signInAnonymously()
        val db = FirebaseFirestore.getInstance()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WifiChecker.checkWifi(this);

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter


        val retrofit = Retrofit.Builder()
            .baseUrl("https://finnhub.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val marketService = retrofit.create(IVantageService::class.java)
        val vantageRepository = VantageRepository(marketService)


        vantageRepository.currentStockPriceData.observe(this, { currentStockPrice ->
                Log.d("STOCK-ADDING-OBSERVER", "onCreate: ${currentStockPrice}")
                if ( !currentStockPrice.isEmpty() ) {
                    Log.d("STOCK-ADDING-RECYCLEVIEW", "${currentStockPrice[currentStockPrice.size - 1].c}")
                    adapter.insertNewSymbol(SymbolItem(name = "${currentStockPrice[currentStockPrice.size - 1].c}") )

/*
                    adapter.insertNewSymbol(SymbolItem(name = "${currentStockPrice[currentStockPrice.size - 1].c}") )
*/
                }
        });

        vantageRepository.getStockCurrentPrice(listOf(
            "NVDA",
            "GWW",
            "RE",
        ), "cg6ekq9r01qjg4hg7i6gcg6ekq9r01qjg4hg7i70")
    }



    override fun onDestroy() {
        super.onDestroy()
        // TODO remove observer
    }
}