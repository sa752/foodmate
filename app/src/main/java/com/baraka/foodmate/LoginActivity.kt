package com.baraka.foodmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        login()
        redirect();
        forgotPassword();
    }

    private  fun login(){
        val login: Button = findViewById(R.id.login);
        login.setOnClickListener {

            val email : TextView = findViewById(R.id.email_login)
            val password: TextView = findViewById(R.id.login_password)
            if (email.text.toString().trim().isEmpty()) {
                email.setError("Email is Required")
                return@setOnClickListener
            }
            if (password.text.toString().trim().isEmpty()) {
                password.setError("Password is Required")
                return@setOnClickListener
            }

            val validEmail = email.text.toString().trim()
            val validPsssword = password.text.toString().trim()

            auth.signInWithEmailAndPassword(validEmail, validPsssword).addOnCompleteListener{
                task ->
                if(task.isSuccessful){
                    startActivity(Intent(this, DashBoardActivity::class.java))
                    finish()
                }else {
                    Toast.makeText(this, "Login unsuccessfull tyr again later", Toast.LENGTH_LONG)
                }
            }

        }

    }

    private fun redirect() {
        val redirectBTN: TextView = findViewById(R.id.redirect_to_signup)
        redirectBTN.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun forgotPassword() {
        val redirectBTN: TextView = findViewById(R.id.forgot_pwd)
        redirectBTN.setOnClickListener {
            startActivity(Intent(this, ForgotPassWordActivity::class.java))
        }

    }

}