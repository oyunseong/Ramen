package com.android.ramen.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface Order {
    val orderNumber: Int        // 주문 순서
    val cookingTime: Long       // 단위 : s, 초
}

@Parcelize
data class Ramen(
    override val orderNumber: Int,
    override val cookingTime: Long = System.currentTimeMillis(),
    val water: Int,                                              // 단위 : ml, 밀리리터
    val powder: Int,                                             // 단위 : %, 퍼센트
    val etc: String     //ArrayList<String>,                     // 기타 재료
) : Order, Parcelable {
    companion object {
        val mockRamen = Ramen(      // 임시 데이터
            orderNumber = 0,
            cookingTime = System.currentTimeMillis(),
            water = 0,
            powder = 0,
            etc = ""
        )
    }
}
