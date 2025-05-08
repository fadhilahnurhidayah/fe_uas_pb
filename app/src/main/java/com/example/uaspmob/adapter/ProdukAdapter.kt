package com.example.uaspmob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uaspmob.R
import com.example.uaspmob.model.Produk
import com.example.uaspmob.util.CurrencyFormatter

class ProdukAdapter(
    private val produkList: MutableList<Produk> = mutableListOf(),
    private val onItemClick: (Produk) -> Unit
) : RecyclerView.Adapter<ProdukAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tv_produk_nama)
        val tvHarga: TextView = view.findViewById(R.id.tv_produk_harga)
        val tvKategori: TextView = view.findViewById(R.id.tv_produk_kategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produk, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produk = produkList[position]

        holder.tvNama.text = produk.nama
        holder.tvHarga.text = CurrencyFormatter.format(produk.harga)
        holder.tvKategori.text = produk.kategori

        holder.itemView.setOnClickListener {
            onItemClick(produk)
        }
    }

    override fun getItemCount() = produkList.size

    fun updateData(newList: List<Produk>) {
        produkList.clear()
        produkList.addAll(newList)
        notifyDataSetChanged()
    }
}
