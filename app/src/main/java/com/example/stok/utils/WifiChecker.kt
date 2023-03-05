package com.example.stok.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.core.content.ContextCompat.getSystemService

class WifiChecker {
    companion object {
        fun checkWifi () {

            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
            val wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager;

            val network = connectivityManager.activeNetwork;
            val capabilities = connectivityManager.getNetworkCapabilities(network);

            val isConnected = capabilities?.let {
                Log.d("STOK_CONNECTION_STATE", "Connection OK")

            } ?:run {
                Log.d("STOK_CONNECTION_STATE", "No connection, retry")

                // TODO toast try to connect
                if ( !wifiManager.isWifiEnabled ) {
                    AlertDialog
                        .Builder(this)
                        .setTitle("Wi-Fi is disabled")
                        .setMessage("Application requires internet connection.")
                        .setPositiveButton("Enable Wi-Fi") { _, _ ->

                        }
                        .setNegativeButton("Cancel", null)
                        .show()
                }

            }
        }
    }
}