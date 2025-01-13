package com.alpha.loginsignup.model

data class Item(val id: Int, val imageRes: Int, val title: String, val description: String)

enum class ItemAction {
    CLICK,
    BUTTON_CLICK
}
