package com.android.ramen.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ramen.R
import com.android.ramen.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private lateinit var ramen: Ramen
    private var _binding: FragmentOrderBinding? = null
    private lateinit var orderViewModel: OrderViewModel

    @SuppressLint("SetTextI18n")
    private val orderAdapter = OrderAdapter(onItemClickListener = {
        val ramen: Ramen = it.product as Ramen
        binding.orderNumberDetail.text = "주문번호 : ${it.id}"
        binding.waterDetail.text = "물의 양 : ${ramen.water}ml"
        binding.powderDetail.text = "파우더 양 : ${ramen.powder}%"
        binding.etcDetail.text = "추가 재료 : ${ramen.etc}"
        binding.timeDetail.text = "경과 시간 : ${ramen.cookingTime}분"
    })

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("++OrderFragment", "++onCreate")
        activity?.run {
            orderViewModel = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
            )[OrderViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.d("++OrderFragment", "++onCreateView")
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOrderRecyclerView()
        ramen = arguments?.getParcelable("ramen") ?: Ramen.mockRamen
        Log.d("++onViewCreated", orderViewModel.orderList.value.toString())

        if (ramen != Ramen.mockRamen) {
            orderViewModel.orderProduct(ramen)
        }

        orderViewModel.event.observe(viewLifecycleOwner) {
            when (it.second) {
                0 -> {
                    orderViewModel.changeEvent(
                        it.first,
                        R.drawable.beginning,
                        it.second,
                        RamenCookingState.BEGINNING
                    )
                }
                1 -> {
                    orderViewModel.changeEvent(
                        it.first,
                        R.drawable.middle,
                        it.second,
                        RamenCookingState.MIDDLE
                    )
                }
                2 -> {
                    orderViewModel.changeEvent(
                        it.first,
                        R.drawable.last,
                        it.second,
                        RamenCookingState.LAST
                    )
                }
                else -> {
                    orderViewModel.changeEvent(
                        it.first,
                        R.drawable.finish,
                        it.second,
                        RamenCookingState.FINISH
                    )
                }
            }
        }

        orderViewModel.orderList.observe(viewLifecycleOwner) {
            try {
                orderAdapter.setOrderList(it)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.HomeFragment)
        }
    }

    private fun setOrderRecyclerView() {
        binding.orderRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = orderAdapter
        }
    }
}