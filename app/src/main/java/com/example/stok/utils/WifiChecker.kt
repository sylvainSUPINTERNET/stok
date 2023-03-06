package com.example.stok.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.core.content.ContextCompat.getSystemService
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.stok.databinding.ActivityMainBinding

class WifiChecker {
    companion object {
        fun checkWifi (ctx:Context) {

            val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
            val wifiManager = ctx.getSystemService(Context.WIFI_SERVICE) as WifiManager;

            val network = connectivityManager.activeNetwork;
            val capabilities = connectivityManager.getNetworkCapabilities(network);

            val isConnected = capabilities?.let {
                Log.d("STOK_CONNECTION_STATE", "Connection OK")

            } ?:run {
                Log.d("STOK_CONNECTION_STATE", "No connection, retry")

                if ( !wifiManager.isWifiEnabled ) {
                    AlertDialog
                        .Builder(ctx)
                        .setTitle("Wi-Fi is disabled")
                        .setMessage("Application requires internet connection.")
                        .setPositiveButton("Enable Wi-Fi") { _, _ ->
                            checkWifi(ctx);
                        }
                        .setNegativeButton("Cancel", { _, _ ->
                            checkWifi(ctx);
                        })
                        .show()
                }

            }
        }
    }
}