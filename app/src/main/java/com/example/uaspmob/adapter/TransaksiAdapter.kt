package com.example.uaspmob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uaspmob.R
import com.example.uaspmob.model.Transaksi
import com.example.uaspmob.util.CurrencyFormatter

class TransaksiAdapter(
    private val transaksiList: MutableList<Transaksi> = mutableListOf(),
    private val onItemClick: (Transaksi) -> Unit
) : RecyclerView.Adapter<TransaksiAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tv_transaksi_id)
        val tvTanggal: TextView = view.findViewById(R.id.tv_transaksi_tanggal)
        val tvTotal: TextView = view.findViewById(R.id.tv_transaksi_total)
        val tvTunai: TextView = view.findViewById(R.id.tv_transaksi_tunai)
        val tvKembalian: TextView = view.findViewById(R.id.tv_transaksi_kembalian)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaksi, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaksi = transaksiList[position]

        with(holder) {
            tvId.text = "Transaksi #${transaksi.id}"
            tvTanggal.text = "Tanggal: ${transaksi.tanggal}"
            tvTotal.text = "Total: ${CurrencyFormatter.format(transaksi.total)}"
            tvTunai.text = "Tunai: ${CurrencyFormatter.format(transaksi.tunai)}"
            tvKembalian.text = "Kembalian: ${CurrencyFormatter.format(transaksi.kembalian)}"

            itemView.setOnClickListener { onItemClick(transaksi) }
        }
    }

    override fun getItemCount() = transaksiList.size

    fun updateData(newList: List<Transaksi>) {
        transaksiList.clear()
        transaksiList.addAll(newList)
        notifyDataSetChanged()
    }
}