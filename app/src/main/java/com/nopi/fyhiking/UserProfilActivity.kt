package com.nopi.fyhiking

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_profil.*
import kotlinx.android.synthetic.main.toolbar_main.*

class UserProfilActivity: AppCompatActivity() {
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profil)
        var username: TextView = findViewById(R.id.txUsername)

        prefManager = PrefManager(this)
        username.text = prefManager.getUsername().toString()

        checkLogin()

        Logout.setOnClickListener {
            prefManager.removeData()
            var intent = Intent(this@UserProfilActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun checkLogin() {
        if (prefManager.isLogin() == false) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}