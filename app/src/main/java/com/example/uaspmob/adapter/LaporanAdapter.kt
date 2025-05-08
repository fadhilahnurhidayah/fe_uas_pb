package com.example.uaspmob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uaspmob.R
import com.example.uaspmob.model.Laporan
import com.example.uaspmob.util.CurrencyFormatter
import java.text.DateFormatSymbols

class LaporanAdapter(
    private val laporanList: MutableList<Laporan> = mutableListOf(),
    private val onItemClick: (Laporan) -> Unit
) : RecyclerView.Adapter<LaporanAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPeriode: TextView = view.findViewById(R.id.tv_laporan_periode)
        val tvPendapatan: TextView = view.findViewById(R.id.tv_laporan_pendapatan)
        val tvProdukTerlaris: TextView = view.findViewById(R.id.tv_laporan_produk_terlaris)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_laporan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val laporan = laporanList[position]
        val monthName = DateFormatSymbols().months[laporan.bulan]

        with(holder) {
            tvPeriode.text = "$monthName ${laporan.tahun}"
            tvPendapatan.text = "Pendapatan: ${CurrencyFormatter.format(laporan.total_pendapatan)}"
            tvProdukTerlaris.text = "Produk Terlaris: ${laporan.produk_terlaris} item"

            itemView.setOnClickListener { onItemClick(laporan) }
        }
    }

    override fun getItemCount() = laporanList.size

    fun updateData(newList: List<Laporan>) {
        laporanList.clear()
        laporanList.addAll(newList)
        notifyDataSetChanged()
    }
}