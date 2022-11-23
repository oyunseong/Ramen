package com.android.ramen

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.android.ramen.databinding.ActivityMainBinding
import com.android.ramen.ui.OrderFragment
import com.android.ramen.ui.OrderViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var orderFragment: OrderFragment
    private var fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
        homeFragment = HomeFragment()
        orderFragment = OrderFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, homeFragment).commit()
    }

    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.primaryNavigationFragment
    }

    fun changeFragment(type: FragmentType) {
        when (type) {
            FragmentType.ORDER -> {  // 홈으로 이동
                try {
                    fragmentManager.beginTransaction().hide(getCurrentFragment()!!).commit()
                    fragmentManager.beginTransaction().show(homeFragment).commit()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            else -> {}
        }
    }

    fun onChangeFragment(view: View, type: FragmentType) {
        view.setOnClickListener {
            when (type) {
                FragmentType.ORDER -> {  // 홈으로 이동
                    try {
                        fragmentManager.beginTransaction().hide(getCurrentFragment()!!).commit()
                        fragmentManager.beginTransaction().show(homeFragment).commit()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                FragmentType.HOME -> { // 오더로 이등
                    try {
                        fragmentManager.beginTransaction().hide(getCurrentFragment()!!).commit()
                        fragmentManager.beginTransaction().show(orderFragment).commit()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }


}