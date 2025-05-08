package com.example.uaspmob.model

data class Transaksi(
    val id: Int,
    val user_id: Int,
    val tanggal: String,
    val total: Int,
    val tunai: Int,
    val kembalian: Int
)