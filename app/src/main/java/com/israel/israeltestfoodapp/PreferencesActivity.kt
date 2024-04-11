package com.israel.israeltestfoodapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preferences)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end insets

        val usernameEditText = findViewById<EditText>(R.id.username_edit_text)

        val savePrefBtn = findViewById<Button>(R.id.save_pref_btn)

        val preferences = getSharedPreferences("storage", MODE_PRIVATE)

        val editor = preferences.edit()
        savePrefBtn.setOnClickListener {
            editor.putString("username", usernameEditText.text.toString())
            editor.apply()
            Toast.makeText( applicationContext,"Preference username ${preferences.getString("username", "")} added", Toast.LENGTH_SHORT).show()
            finish()
        }


        val clearPrefBtn = findViewById<Button>(R.id.clear_pref_btn)
        clearPrefBtn.setOnClickListener {
            editor.clear()
            editor.apply()
            Toast.makeText(applicationContext, "Preferences cleared", Toast.LENGTH_SHORT).show()
            finish()
        }
    }// end onCreate
}// end PreferencesActivity