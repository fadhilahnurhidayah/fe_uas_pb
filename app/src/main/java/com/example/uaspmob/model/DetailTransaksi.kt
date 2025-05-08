package com.example.uaspmob.model

data class DetailTransaksi(
    val id: Int,
    val transaksi_id: Int,
    val produk_id: Int,
    val qty: Int,
    val subtotal: Int
)