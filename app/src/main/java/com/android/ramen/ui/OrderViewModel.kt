package com.android.ramen.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.ramen.ui.Ramen

class OrderViewModel : ViewModel() {
    private val _cookingList = MutableLiveData<List<Ramen>>()
    val cookingList: LiveData<List<Ramen>> get() = _cookingList

    companion object {
        var orderNumber = 0
    }

    init {
        Log.d("+++OrderViewModel", "생성자")
    }


    // 조리 상태 확인
    fun getRamenState() {
        cookingList.value
    }

    fun setOrderList(list: List<Ramen>) {
        _cookingList.value = list
    }

    // 주문 버튼 클릭시 동작
    fun setOrder(ramen: Ramen) {
        orderNumber++
        val list: MutableList<Ramen> = arrayListOf<Ramen>()
        list.addAll(cookingList.value ?: emptyList())
        list.addAll(listOf(ramen.copy(orderNumber = orderNumber)))
        _cookingList.value = list
    }
}