package com.example.weatherapp.other

enum class AdapterTypes {
    SPACE_VIEW,
    ITEM_VIEW,
    PLACE_HOLDER,
    TRANSPARENT,
    PAYMENTS_HEADER_HOLDER;

    val type: Int
        get() = when (this) {
            SPACE_VIEW -> 0
            ITEM_VIEW -> 1
            PLACE_HOLDER -> 2
            TRANSPARENT -> 3
            PAYMENTS_HEADER_HOLDER -> 4
        }
}