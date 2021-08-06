package ir.hajhosseinico.yarabatmancollection.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ir.hajhosseinico.yarabatmancollection.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * MainActivity
     * Using Navigation Component for managing fragments
     * Using Fragment factory
     * Using custom navigation host fragment (to solve screen rotation bug of navigation component)
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}