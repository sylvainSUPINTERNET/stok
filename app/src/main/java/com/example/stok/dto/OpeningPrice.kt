package com.example.stok.dto


/*
{
    "c": [
        121.96,
        121.03
    ],
    "h": [
        123.21,
        121.17
    ],
    "l": [
        121.26,
        119.16
    ],
    "o": [
        122.54,
        120.4
    ],
    "s": "ok",
    "t": [
        1615420800,
        1615507200
    ],
    "v": [
        103026514,
        88105050
    ]
}
 */

data class OpeningPrice(
    val c : List<Double>,
    val h : List<Double>,
    val l : List<Double>,
    val o : List<Double>,
    val s : String,
    val t : List<Long>,
    val v : List<Long>
)
