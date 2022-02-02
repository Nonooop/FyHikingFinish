package com.nopi.fyhiking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.nopi.fyhiking.activities.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    private lateinit var prefManager: PrefManager
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

            val btnLog: Button = findViewById(R.id.btn_Login1)
            val tiEmail: TextInputLayout = findViewById(R.id.tiEmail1)
            val tiPass: TextInputLayout = findViewById(R.id.tiPassword1)
            var name = tiEmail.editText?.text.toString()
            var email = tiEmail.editText?.text.toString()
            var pass = tiPass.editText?.text.toString()

            init()
            checkLogin()
            val db = DBHelper(this, null)

            btnLog.setOnClickListener {
                if (tiEmail.editText?.text?.isNotEmpty() == false &&
                    tiPass.editText?.text?.isNotEmpty() == false)
                    Toast.makeText(this, "Data Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
                else{
                    var logindt:Boolean = db.LoginDatas(tiEmail.editText?.text.toString(),name,tiPass.editText?.text.toString());
                    if (logindt==true){
                        prefManager.setLoggin(true)
                        prefManager.setUsername(tiEmail.editText?.text.toString())
                        Toast.makeText(this, "Login berhasil!",
                            Toast.LENGTH_SHORT).show()
                        var intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "Login gagal!",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }

            ke_daftar.setOnClickListener{
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

        private fun init(){
            prefManager = PrefManager(this)
        }
    
        private fun checkLogin(){
            if (prefManager.isLogin()!!){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
}
