package com.android.ramen.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Order(
    val id: Int,
    val product: Product,
    val image: Int
)

abstract class Product

interface Boilable {
    val cookingTime: Int
    val ramenCookingState: RamenCookingState
}

@Parcelize
data class Ramen(
    val water: Int,
    val powder: Int,
    val etc: String,
    override val ramenCookingState: RamenCookingState = RamenCookingState.BEGINNING,
    override val cookingTime: Int = 0

) : Product(), Boilable, Parcelable {
    companion object {
        val mockRamen = Ramen(
            water = 0,
            powder = 0,
            etc = "",
        )
    }
}

enum class RamenCookingState {
    BEGINNING, MIDDLE, LAST, FINISH
}
