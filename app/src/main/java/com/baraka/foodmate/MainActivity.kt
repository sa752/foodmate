package com.baraka.foodmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser;
        if(currentUser != null){
            startActivity(Intent(this, CustomerDashBoardActivity::class.java))
            finish()
        }


        Toast.makeText(this, "Welcome to our Resturant",
            Toast.LENGTH_LONG
        ).show()

        val loginButton: Button =findViewById(R.id.button_login)
        val signUpButton: Button = findViewById(R.id.button_signup)

        loginButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}