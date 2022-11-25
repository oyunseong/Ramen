package com.android.ramen.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ramen.databinding.FragmentOrderBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class OrderFragment : Fragment() {
    private lateinit var ramen: Ramen
    private var _binding: FragmentOrderBinding? = null
    private val orderViewModel by viewModels<OrderViewModel>()
    private val orderAdapter = OrderAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("++OrderFragment", "++onCreate")


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
        Log.d("++onViewCreated", orderViewModel.cookingList.value.toString())

        if (ramen != Ramen.mockRamen) {
            orderViewModel.setOrder(ramen)
        }

        orderViewModel.cookingList.observe(viewLifecycleOwner) {
            try {
//                orderAdapter.setOrderList(it)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.buttonSecond.setOnClickListener {
//            MainActivity().onChangeFragment(it, FragmentType.HOME)
//            findNavController().navigate(R.id.FirstFragment)
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