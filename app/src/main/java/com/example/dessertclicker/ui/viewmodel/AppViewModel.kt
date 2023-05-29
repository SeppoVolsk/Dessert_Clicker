package com.example.dessertclicker.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val desserts = Datasource.dessertList
    private var dessert: Dessert = determineDessertToShow()
    private var sold: Int = 0
    private var revenue: Int = 0

    private val _state = MutableStateFlow(
        AppState(dessert, sold, revenue)
    )
    val state: StateFlow<AppState> = _state.asStateFlow()

    fun update() {
        revenue += dessert.price
        sold++
        dessert = determineDessertToShow()
        _state.update {
            AppState(dessert, sold, revenue)
        }
    }
    private fun determineDessertToShow(): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (sold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
        return dessertToShow
    }
}