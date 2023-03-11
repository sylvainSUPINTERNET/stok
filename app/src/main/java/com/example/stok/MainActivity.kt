package com.example.stok

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.stok.databinding.ActivityMainBinding
import com.example.stok.ui.symbol.SymbolItem
import com.example.stok.ui.symbol.SymbolRecycleViewAdapter
import com.example.stok.utils.WifiChecker
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        FirebaseAuth.getInstance().signInAnonymously()
        val db = FirebaseFirestore.getInstance()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WifiChecker.checkWifi(this);

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)



        val items = listOf (
                SymbolItem(name="HGEL"),
                SymbolItem(name="JEJnx"),
                SymbolItem(name="JEJnx"),
                SymbolItem(name="JEJnx")    )

        recyclerView.adapter = SymbolRecycleViewAdapter(items)
    }
}