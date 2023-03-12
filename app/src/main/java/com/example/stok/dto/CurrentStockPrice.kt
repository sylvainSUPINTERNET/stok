package com.example.stok.dto


/*
    e.g :
    "c": 148.5,
    "d": -2.09,
    "dp": -1.3879,
    "h": 150.94,
    "l": 147.61,
    "o": 150.21,
    "pc": 150.59,
    "t": 1678482005
 */

data class CurrentStockPrice (
    val c : Double,
    val d : Double,
    val dp : Double,
    val h : Double,
    val l : Double,
    val o : Double,
    val pc : Double,
    val t : Long
)