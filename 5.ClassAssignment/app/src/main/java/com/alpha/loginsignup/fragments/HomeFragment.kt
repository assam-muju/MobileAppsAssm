package com.alpha.loginsignup.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpha.loginsignup.R
import com.alpha.loginsignup.adapter.ItemAdapter
import com.alpha.loginsignup.databinding.FragmentHomeBinding
import com.alpha.loginsignup.model.Item
import com.alpha.loginsignup.model.ItemAction

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ItemAdapter(getDummyItems()) { item, action ->
            when (action) {
                ItemAction.CLICK -> {
                    Log.d("RecyclerView", "Item clicked: ${item.id}")
                    Toast.makeText(requireContext(), "Item clicked: ${item.id}", Toast.LENGTH_SHORT).show()
                }
                ItemAction.BUTTON_CLICK -> {
                    Log.d("RecyclerView", "Button clicked for item: ${item.id}")
                    Toast.makeText(requireContext(), "Button clicked for item: ${item.id}", Toast.LENGTH_SHORT).show()
                }

            }
        }
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getDummyItems(): List<Item> {
        return listOf(
            Item(id = 1, imageRes = R.drawable.img, title = "Item 1"),
            Item(id = 2, imageRes = R.drawable.img_1, title = "Item 2"),
            Item(id = 3, imageRes = R.drawable.img_5, title = "Item 3"),
            Item(id = 4, imageRes = R.drawable.img_2, title = "Item 4"),
            Item(id = 5, imageRes = R.drawable.img_3, title = "Item 5")
        )
    }
}

