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


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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







/*        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }
}