package com.nopi.fyhiking

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nopi.fyhiking.activities.MainActivity
import com.nopi.fyhiking.activities.NoteActivity
import com.nopi.fyhiking.activities.PeralatanActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar_main.*

class HomeActivity : AppCompatActivity() {
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var username: TextView = findViewById(R.id.txUsername)

        prefManager = PrefManager(this)
        username.text = prefManager.getUsername().toString()
        checkLogin()

        infoGunung.setOnClickListener {
            val intent = Intent(this@HomeActivity, MainActivity::class.java)
            startActivity(intent)
        }

        Peralatan.setOnClickListener {
            val intent = Intent(this@HomeActivity, PeralatanActivity::class.java)
            startActivity(intent)
        }

        Catatan.setOnClickListener{
            val intent = Intent(this@HomeActivity, NoteActivity::class.java)
            startActivity(intent)
        }
        userProfil.setOnClickListener{
            val intent = Intent(this@HomeActivity, UserProfilActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkLogin(){
        if (prefManager.isLogin() == false){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}