package com.nopi.fyhiking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnSignup: Button = findViewById(R.id.btn_signup)
        val tiName: TextInputLayout = findViewById(R.id.tf_uname)
        val tiPass: TextInputLayout = findViewById(R.id.tf_pass)
        val tiEmail: TextInputLayout = findViewById(R.id.tf_email)
        var name = tiName.editText?.text.toString()
        var pass = tiPass.editText?.text.toString()
        var email = tiEmail.editText?.text.toString()
        val db = DBHelper(this, null)

        ke_masuk.setOnClickListener{
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }


        btnSignup.setOnClickListener {
            if(tiName.editText?.text?.isNotEmpty() == true && tiEmail.editText?.text?.isNotEmpty() == true && tiPass.editText?.text?.isNotEmpty() == true){
                if(db.addData(tiName.editText?.text.toString(), tiEmail.editText?.text.toString(),tiPass.editText?.text.toString())){
                    Toast.makeText(this, "Data Tersimpan!", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(this, "Data Sudah Terdaftar!", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Data Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}