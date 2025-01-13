package com.alpha.loginsignup.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpha.loginsignup.R
import com.alpha.loginsignup.adapter.ItemAdapter
import com.alpha.loginsignup.databinding.FragmentHomeBinding
import com.alpha.loginsignup.model.Item
import com.alpha.loginsignup.model.ItemAction
import com.alpha.loginsignup.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemAdapter
    private val viewModel: HomeViewModel by viewModels()

    private var allItems: List<Item> = emptyList()

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
        adapter = ItemAdapter { item, action ->
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


        // Observe ViewModel StateFlow
        lifecycleScope.launchWhenStarted {
            viewModel.filteredItems.collect { items ->
                allItems = items
                adapter.submitList(items)
            }
        }

        binding.searchView.doOnTextChanged { text, start, before, count ->
            viewModel.onSearchQueryChanged(text.toString())

        }


        // Set initial items
        viewModel.setItems(getDummyItems())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getDummyItems(): List<Item> {
        return listOf(
            Item(id = 1, imageRes = R.drawable.img, title = "Chocolate Cake", description = "Delicious and moist"),
            Item(id = 2, imageRes = R.drawable.img_1, title = "Pasta Salad", description = "Healthy and tasty"),
            Item(id = 3, imageRes = R.drawable.img_5, title = "Grilled Chicken", description = "Perfectly cooked"),
            Item(id = 4, imageRes = R.drawable.img_2, title = "Fruit Tart", description = "Sweet and fresh"),
            Item(id = 5, imageRes = R.drawable.img_3, title = "Burger", description = "Juicy and flavorful")
        )
    }
}
