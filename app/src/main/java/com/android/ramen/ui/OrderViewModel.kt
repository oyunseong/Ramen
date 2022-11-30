package com.android.ramen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class OrderViewModel : ViewModel() {
    private val _orderList = MutableLiveData<List<Ramen>>()
    val orderList: LiveData<List<Ramen>> get() = _orderList

    private val _notionOrder = MutableLiveData<Int>()
    val notionOrder: LiveData<Int> get() = _notionOrder

    private val _finishOrder = MutableLiveData<Int>()
    val finishOrder: LiveData<Int> get() = _finishOrder

    companion object {
        var orderNumber = 0
        const val NOTION_TIME = 60
        const val FINISH_ORDER_TIME = 120
    }

    /**
     * @param i : 주문번호, orderNumber
     * */
    private fun timer(i: Int) {
        var timer: Timer = Timer()
        var cnt: Int = 0

        val timerTask = object : TimerTask() {
            override fun run() {
                if (cnt == NOTION_TIME) {
                    _notionOrder.postValue(i)
                }
                if (cnt >= FINISH_ORDER_TIME) {
                    timer.cancel()
                    _finishOrder.postValue(i)
                }
                cnt++
            }
        }
        timer.schedule(timerTask, 0, 1000)
    }

    /**
     * @param i : 주문번호, orderNumber
     * */
    fun popOrder(i: Int) {
        try {
            orderList.value?.forEachIndexed { index, it ->
                if (it.orderNumber == i) {
                    val list: MutableList<Ramen> = mutableListOf()
                    list.addAll(orderList.value ?: emptyList())
                    list.removeAt(index)
                    _orderList.value = list
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    // 주문 버튼 클릭시 동작
    fun setOrder(ramen: Ramen) {
        orderNumber++
        val list: MutableList<Ramen> = arrayListOf<Ramen>()
        list.addAll(orderList.value ?: emptyList())
        list.addAll(listOf(ramen.copy(orderNumber = orderNumber)))
        _orderList.value = list
        timer(orderNumber)
    }
}