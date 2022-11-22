package com.android.ramen

interface Order {
    val orderNumber: Int        // 주문 순서
    val cookingTime: Int        // 단위 : s, 초
}

data class Ramen(
    override val orderNumber: Int,
    override val cookingTime: Int,
    val water: Int,             // 단위 : ml, 밀리리터
    val powder: Int,            // 단위 : %, 퍼센트
    val etc: ArrayList<String>,// 기타 재료
) : Order
