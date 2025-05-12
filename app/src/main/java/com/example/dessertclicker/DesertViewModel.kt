package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DesertViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DesertClickerAppState(deserts=mutableListOf(
        Dessert(R.drawable.cupcake,10,100),
    )))
    val uiState: StateFlow<DesertClickerAppState> = _uiState.asStateFlow()
    fun updateRevenueAnddeterminDesertToShow(){
        val updatedRev=_uiState.value.revenue.plus(_uiState.value.currentDessertPrice)
        val updatedDesertSold=_uiState.value.desertsSold.plus(1)
        _uiState.update { currentState->currentState.copy(
            revenue = updatedRev,
            desertsSold = updatedDesertSold
        ) }
        val desertObject = determineDessertToShow(uiState.value.desertsSold)
        _uiState.update {
            currentState -> currentState.copy(
                currentDesertIndex = desertObject.imageId,
                currentDessertPrice = desertObject.price
            )
        }
    }
    private fun determineDessertToShow(
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = _uiState.value.deserts.first()
        for (dessert in _uiState.value.deserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }


}