package com.alpha.loginsignup.viewmodel

import androidx.lifecycle.ViewModel
import com.alpha.loginsignup.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    private val _filteredItems = MutableStateFlow<List<Item>>(emptyList())
    val filteredItems: StateFlow<List<Item>> get() = _filteredItems

    fun setItems(items: List<Item>) {
        _items.value = items
        _filteredItems.value = items
    }

    fun onSearchQueryChanged(query: String) {
        if (query.length >= 3) {
            _filteredItems.value = _items.value.filter {
                it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }
        } else {
            _filteredItems.value = _items.value
        }
    }
}
