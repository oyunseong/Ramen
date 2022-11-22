package com.android.ramen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _cookingList = MutableLiveData<List<Ramen>>()
    val cookingList: LiveData<List<Ramen>> get() = _cookingList

    // 조리 상태 확인
    fun getRamenState() {
        cookingList.value
    }

    // 주문 버튼 클릭시 동작
    fun setOrder(list: List<Ramen>) {
        _cookingList.value = list
    }
}