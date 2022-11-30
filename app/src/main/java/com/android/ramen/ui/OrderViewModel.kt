package com.android.ramen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.ramen.R
import com.android.ramen.SingleLiveEvent
import java.util.*

class OrderViewModel : ViewModel() {
    private val _orderList = MutableLiveData<List<Order>>()
    val orderList: LiveData<List<Order>> get() = _orderList

    private val _event = SingleLiveEvent<Pair<Int, Int>>()
    val event: LiveData<Pair<Int, Int>> get() = _event

    companion object {
        /**
         * 원래는 주문번호 생성을 위해 서버 혹은 로컬 데이터 베이스에서 저장하는 것이 적합하지만 간단한 테스트를 위해 전역변수로 선언
         * */
        private var orderNumberCounter = 0
    }

    /**
     * 1분이 지나면 갱신
     * min값 1증가
     * */
    private fun startTimer(orderNumber: Int) {
        val timer = Timer()
        var cnt = 0

        val timerTask = object : TimerTask() {
            override fun run() {
                when (cnt) {
                    0 -> {
                        _event.postValue(Pair(orderNumber, cnt))
                    }
                    1 -> {
                        _event.postValue(Pair(orderNumber, cnt))
                    }
                    2 -> {
                        _event.postValue(Pair(orderNumber, cnt))
                    }
                    else -> {
                        _event.postValue(Pair(orderNumber, cnt))
                        timer.cancel()
                    }
                }
                cnt++
            }
        }
        timer.schedule(timerTask, 0, 5000)
    }

    fun orderProduct(product: Product) {
        orderNumberCounter++
        val order = Order(
            id = orderNumberCounter, product = product, image = R.drawable.beginning
        )
        _orderList.value = (orderList.value ?: emptyList()) + listOf(order)
        startTimer(orderNumberCounter)
    }

    fun changeEvent(id: Int, image: Int, time: Int, ramenCookingState: RamenCookingState) {
        _orderList.value = _orderList.value?.map {
            if (it.id == id) {
                val ramen = it.product as Ramen
                it.copy(
                    image = image,
                    product = ramen.copy(ramenCookingState = ramenCookingState, cookingTime = time)
                )
            } else {
                it
            }
        }
    }

    fun setOrderList(order: List<Order>) {
        _orderList.value = order
    }
}