package com.example.uaspmob.ui.produk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.uaspmob.adapter.ProdukAdapter
import com.example.uaspmob.databinding.FragmentProdukBinding
import com.example.uaspmob.model.Produk

class ProdukFragment : Fragment() {
    private var _binding: FragmentProdukBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ProdukAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProdukBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadDummyData()
    }

    private fun setupRecyclerView() {
        adapter = ProdukAdapter(mutableListOf()) { _ ->
            // Handle item click
        }

        binding.rvProduk.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = this@ProdukFragment.adapter
        }
    }

    private fun loadDummyData() {
        val dummyProduk = listOf(
            Produk(1, "Smartphone", 5000000, "Elektronik", 4500000),
            Produk(2, "Laptop", 10000000, "Elektronik", 9000000),
            Produk(3, "T-Shirt", 150000, "Fashion", 100000),
            Produk(4, "Jeans", 300000, "Fashion", 200000)
        )
        adapter.updateData(dummyProduk)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}