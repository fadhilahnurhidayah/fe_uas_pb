package com.example.uaspmob.ui.transaksi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uaspmob.databinding.FragmentTransaksiBinding
import com.example.uaspmob.model.Transaksi
import com.example.uaspmob.adapter.TransaksiAdapter
import java.text.SimpleDateFormat
import java.util.*

class TransaksiFragment : Fragment() {
    private var _binding: FragmentTransaksiBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TransaksiAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransaksiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TransaksiAdapter(mutableListOf()) { transaksi ->
            // Handle item click
        }

        binding.rvTransaksi.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = this@TransaksiFragment.adapter
        }

        loadDummyData()
    }

    private fun loadDummyData() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val dummyTransaksi = listOf(
            Transaksi(1, 1, dateFormat.format(Date()), 500000, 600000, 100000),
            Transaksi(2, 1, dateFormat.format(Date()), 300000, 350000, 50000),
            Transaksi(3, 1, dateFormat.format(Date(System.currentTimeMillis() - 86400000)), 150000, 200000, 50000)
        )
        adapter.updateData(dummyTransaksi)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}