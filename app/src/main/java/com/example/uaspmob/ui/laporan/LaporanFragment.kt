package com.example.uaspmob.ui.laporan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uaspmob.databinding.FragmentLaporanBinding
import com.example.uaspmob.model.Laporan
import com.example.uaspmob.adapter.LaporanAdapter
import com.example.uaspmob.util.CurrencyFormatter
import java.util.*

class LaporanFragment : Fragment() {
    private var _binding: FragmentLaporanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: LaporanAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadDummyData()
    }

    private fun setupRecyclerView() {
        adapter = LaporanAdapter(mutableListOf()) { laporan ->
            // Handle item click
        }

        binding.rvLaporan.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = this@LaporanFragment.adapter
        }
    }

    private fun loadDummyData() {
        val dummyLaporan = listOf(
            Laporan(1, 1, Calendar.JANUARY, 2023, 12500000, 15),
            Laporan(2, 1, Calendar.FEBRUARY, 2023, 18750000, 22),
            Laporan(3, 1, Calendar.MARCH, 2023, 21000000, 28),
            Laporan(4, 1, Calendar.APRIL, 2023, 16500000, 19)
        )
        adapter.updateData(dummyLaporan)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}