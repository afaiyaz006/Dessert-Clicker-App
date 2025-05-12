package com.example.dessertclicker
import com.example.dessertclicker.model.Dessert

data class DesertClickerAppState (

    var revenue:Int=0,
    var desertsSold:Int=0,
    var currentDessertPrice:Int=0,
    var currentDesertIndex:Int=R.drawable.cupcake,
    var deserts:List<Dessert>,

)