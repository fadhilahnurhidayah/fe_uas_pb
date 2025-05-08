package com.example.uaspmob.util
import com.example.uaspmob.model.*

object DummyDataManager {

    // Dummy User Data
    val users = listOf(
        User(
            id = 1,
            username = "admin",
            password = "admin123",
            role = "admin"
        ),
        User(
            id = 2,
            username = "kasir",
            password = "kasir123",
            role = "kasir"
        )
    )

    // Dummy Produk Data
    val produkList = mutableListOf(
        Produk(
            id = 1,
            nama = "Nasi Goreng",
            harga = 15000,
            kategori = "Makanan",
            harga_beli = 10000
        ),
        Produk(
            id = 2,
            nama = "Mie Goreng",
            harga = 12000,
            kategori = "Makanan",
            harga_beli = 8000
        ),
        Produk(
            id = 3,
            nama = "Es Teh",
            harga = 5000,
            kategori = "Minuman",
            harga_beli = 2000
        ),
        Produk(
            id = 4,
            nama = "Es Jeruk",
            harga = 6000,
            kategori = "Minuman",
            harga_beli = 3000
        ),
        Produk(
            id = 5,
            nama = "Ayam Goreng",
            harga = 18000,
            kategori = "Makanan",
            harga_beli = 12000
        ),
        Produk(
            id = 6,
            nama = "Soto Ayam",
            harga = 16000,
            kategori = "Makanan",
            harga_beli = 11000
        ),
        Produk(
            id = 7,
            nama = "Kopi Hitam",
            harga = 7000,
            kategori = "Minuman",
            harga_beli = 3500
        ),
        Produk(
            id = 8,
            nama = "Kopi Susu",
            harga = 9000,
            kategori = "Minuman",
            harga_beli = 5000
        )
    )

    // Dummy Transaksi Data
    val transaksiList = mutableListOf(
        Transaksi(
            id = 1,
            user_id = 2,
            tanggal = "2025-05-07",
            total = 33000,
            tunai = 50000,
            kembalian = 17000
        ),
        Transaksi(
            id = 2,
            user_id = 2,
            tanggal = "2025-05-08",
            total = 27000,
            tunai = 30000,
            kembalian = 3000
        )
    )

    // Dummy Detail Transaksi Data
    val detailTransaksiList = mutableListOf(
        DetailTransaksi(
            id = 1,
            transaksi_id = 1,
            produk_id = 1,
            qty = 1,
            subtotal = 15000
        ),
        DetailTransaksi(
            id = 2,
            transaksi_id = 1,
            produk_id = 3,
            qty = 2,
            subtotal = 10000
        ),
        DetailTransaksi(
            id = 3,
            transaksi_id = 1,
            produk_id = 8,
            qty = 1,
            subtotal = 8000
        ),
        DetailTransaksi(
            id = 4,
            transaksi_id = 2,
            produk_id = 2,
            qty = 1,
            subtotal = 12000
        ),
        DetailTransaksi(
            id = 5,
            transaksi_id = 2,
            produk_id = 3,
            qty = 3,
            subtotal = 15000
        )
    )

    // Dummy Laporan Data
    val laporanList = mutableListOf(
        Laporan(
            id = 1,
            user_id = 2,
            bulan = 5,
            tahun = 2025,
            total_pendapatan = 60000,
            produk_terlaris = 3
        )
    )

    // Helper function to get product by ID
    fun getProdukById(id: Int): Produk? {
        return produkList.find { it.id == id }
    }

    // Helper function to get transaction details
    fun getDetailByTransaksiId(transaksiId: Int): List<DetailTransaksi> {
        return detailTransaksiList.filter { it.transaksi_id == transaksiId }
    }

    // Helper function to get User by credentials
    fun login(username: String, password: String): User? {
        return users.find { it.username == username && it.password == password }
    }

    // Helper function to create new transaction
    fun createTransaksi(userId: Int, total: Int, tunai: Int, kembalian: Int): Transaksi {
        val newId = transaksiList.maxOf { it.id } + 1
        val transaksi = Transaksi(
            id = newId,
            user_id = userId,
            tanggal = "2025-05-08", // Current date for demo
            total = total,
            tunai = tunai,
            kembalian = kembalian
        )
        transaksiList.add(transaksi)
        return transaksi
    }

    // Helper function to create detail transaksi
    fun createDetailTransaksi(transaksiId: Int, produkId: Int, qty: Int, subtotal: Int): DetailTransaksi {
        val newId = detailTransaksiList.maxOf { it.id } + 1
        val detailTransaksi = DetailTransaksi(
            id = newId,
            transaksi_id = transaksiId,
            produk_id = produkId,
            qty = qty,
            subtotal = subtotal
        )
        detailTransaksiList.add(detailTransaksi)
        return detailTransaksi
    }

    // Filter products by category
    fun getProdukByKategori(kategori: String): List<Produk> {
        return produkList.filter { it.kategori == kategori }
    }

    // Search products by name
    fun searchProduk(keyword: String): List<Produk> {
        return produkList.filter { it.nama.contains(keyword, ignoreCase = true) }
    }
}