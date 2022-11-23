package com.android.ramen.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.ramen.ui.Ramen

class OrderViewModel : ViewModel() {
    private val _cookingList = MutableLiveData<List<Ramen>>()
    val cookingList: LiveData<List<Ramen>> get() = _cookingList
    private val list = arrayListOf<Ramen>()

    companion object {
        var orderNumber = 0
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
        cookingList.value?.forEachIndexed { i, v ->
            list.add(v)
        }
        val ramen1 = ramen.copy(orderNumber = orderNumber)
        list.add(ramen1)
        _cookingList.value = list
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("++onCleared", "불림")
    }
}