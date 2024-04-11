package com.israel.israeltestfoodapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }// end insets

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val layoutManager = LinearLayoutManager(applicationContext)

        recyclerView.layoutManager = layoutManager

        val foodRecyclerAdapter = RecyclerAdapter(applicationContext)

        val foodRecyclerAdapter2 = FoodRecyclerAdapter(applicationContext)

        recyclerView.adapter = foodRecyclerAdapter2

        val bottomSheetContainer = findViewById<FrameLayout>(R.id.bottom_sheet_container)
        BottomSheetBehavior.from(bottomSheetContainer).apply {
            peekHeight = 170
            state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val btnMore = findViewById<Button>(R.id.btn_more)
        btnMore.setOnClickListener {
            val intent = Intent(applicationContext, PreferencesActivity::class.java)
            startActivity(intent)
        }



    } // end onCreate

    override fun onResume() {
        super.onResume()
        updateGreetingText()
    }

    fun updateGreetingText() {
        val preferences = getSharedPreferences("storage", MODE_PRIVATE)
        val usernameGreeting = findViewById<TextView>(R.id.username_greeting)
        usernameGreeting.text = "Welcome ${preferences.getString("username", "")}"
    }
} // end MainActivity