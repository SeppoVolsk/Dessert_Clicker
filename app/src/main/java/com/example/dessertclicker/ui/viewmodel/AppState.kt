package com.example.dessertclicker.ui.viewmodel

import com.example.dessertclicker.model.Dessert

data class AppState(val dessert: Dessert, val sold: Int, val revenue: Int)
