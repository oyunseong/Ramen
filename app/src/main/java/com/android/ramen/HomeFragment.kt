package com.android.ramen

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.ramen.databinding.FragmentHomeBinding
import com.android.ramen.ui.OrderViewModel
import com.android.ramen.ui.Ramen
import com.android.ramen.ui.RamenCookingState
import com.android.ramen.utils.hideKeyboard


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var water: String
    private lateinit var powder: String
    private lateinit var etc: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.OrderFragment)
        }

        binding.orderButton.setOnClickListener {
            it.hideKeyboard()
            if (orderOptionCheck()) {
                showDialog()
            } else {
                return@setOnClickListener
            }
        }
        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.ImageFragment)
        }
    }

    private fun orderOptionCheck(): Boolean {
        try {
            water = binding.water.text.toString()
            powder = binding.powder.text.toString()
            etc = binding.etc.text.toString()
            val w = water.toInt()
            val p = powder.toInt()

            if (w <= 0 || p <= 0 || p > 100) {
                Toast.makeText(requireContext(), "????????? ?????? ??????????????????.", Toast.LENGTH_SHORT).show()
                return false
            } else if (w in 1..249) {
                Toast.makeText(requireContext(), "????????? ??? ?????? ????????????.", Toast.LENGTH_SHORT).show()
            }
            return true
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "????????? ?????? ??????????????????.", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
            return false
        }
    }


    private fun showDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        water = binding.water.text.toString()
        powder = binding.powder.text.toString()
        etc = binding.etc.text.toString()
        builder.setTitle("????????? ??????").setMessage("?????? ??? : ${water}ml\n????????? ??? : ${powder}%\n?????? ?????? : $etc")

        builder.setPositiveButton("????????????", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                try {
                    val ramen = Ramen(
                        water = water.toInt(),
                        powder = powder.toInt(),
                        etc = etc,
                        ramenCookingState = RamenCookingState.BEGINNING
                    )
                    val bundle = bundleOf(
                        "ramen" to ramen
                    )
                    findNavController().navigate(R.id.orderFragment, bundle)
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "??? ??? ?????? ?????? ??????.", Toast.LENGTH_SHORT).show()
                }
            }
        })
        builder.setNegativeButton("??????", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }
        })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}