package com.android.ramen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.android.ramen.databinding.ActivityMainBinding
import com.android.ramen.ui.OrderFragment

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
}