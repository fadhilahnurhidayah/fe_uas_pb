package com.example.uaspmob.model

data class Laporan(
    val id: Int,
    val user_id: Int,
    val bulan: Int,
    val tahun: Int,
    val total_pendapatan: Int,
    val produk_terlaris: Int
)