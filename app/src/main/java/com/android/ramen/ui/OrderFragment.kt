package com.android.ramen.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ramen.R
import com.android.ramen.databinding.FragmentOrderBinding
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class OrderFragment : Fragment() {
    private lateinit var ramen: Ramen
    private var _binding: FragmentOrderBinding? = null
    private lateinit var orderViewModel: OrderViewModel
    private val orderAdapter = OrderAdapter(onItemClickListener = { it ->
        binding.orderNumberDetail.text = "주문번호 : ${it.orderNumber.toString()}"
        binding.waterDetail.text = "물의 양 : ${it.water.toString()}ml"
        binding.powderDetail.text = "파우더 양 : ${it.powder.toString()}%"
        binding.etcDetail.text = "추가 재료 : ${it.etc}"
        val simpleFormat = SimpleDateFormat("HH:mm:ss")
        binding.timeDetail.text = "조리 시작시간 : ${simpleFormat.format(it.orderStartTime)}"
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
        ramen = arguments?.getParcelable<Ramen>("ramen") ?: Ramen.mockRamen
        Log.d("++onViewCreated", orderViewModel.orderList.value.toString())

        if (ramen != Ramen.mockRamen) {
            orderViewModel.setOrder(ramen)
        }

        orderViewModel.orderList.observe(viewLifecycleOwner) {
            try {
                orderAdapter.setOrderList(it)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        orderViewModel.notionOrder.observe(viewLifecycleOwner) {
            try {
                Toast.makeText(requireContext(), "주문번호 : $it 라면이 1분 경과하였습니다.", Toast.LENGTH_SHORT)
                    .show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.HomeFragment)
        }
        orderViewModel.finishOrder.observe(viewLifecycleOwner) {
            orderViewModel.popOrder(it)
        }
    }

    private fun setOrderRecyclerView() {
        binding.orderRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = orderAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("++OrderFragment", "++onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("++OrderFragment", "++onDestroy")
    }
}