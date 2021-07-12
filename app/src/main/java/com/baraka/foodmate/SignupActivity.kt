package com.baraka.foodmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference?.child("profile")

        redirect()
        register()
    }

    private fun register() {
        val signup: Button = findViewById(R.id.signup)
        signup.setOnClickListener {

            val firstName: TextView = findViewById(R.id.firstNameInput)
            val lastName: TextView = findViewById(R.id.lastNameInput)
            val email: TextView = findViewById(R.id.email_sign_up)
            val password: TextView = findViewById(R.id.s_password)

            if (firstName.text.toString().trim().isEmpty()) {
                firstName.setError("First name is Required")
                return@setOnClickListener
            }
            if (lastName.text.toString().trim().isEmpty()) {
                lastName.setError("Last name is Required")
                return@setOnClickListener
            }
            if (email.text.toString().trim().isEmpty()) {
                email.setError("Email is Required")
                return@setOnClickListener
            }
            if (password.text.toString().trim().isEmpty()) {
                password.setError("Password is Required")
                return@setOnClickListener
            }

            val validEmail = email.text.toString().trim();
            val validPsssword = password.text.toString().trim();
            val validFirstName = firstName.text.toString().trim();
            val validLastName = lastName.text.toString().trim();

            auth.createUserWithEmailAndPassword(validEmail, validPsssword).addOnCompleteListener{
                task->
                if(task.isSuccessful){
                    val currentUser = auth.currentUser
                    val currentUserDB = databaseReference?.child(currentUser?.uid!!)
                    currentUserDB?.child("firstname")?.setValue(validFirstName)
                    currentUserDB?.child("lastname")?.setValue(validLastName)
                    Toast.makeText(this, "Sign up successfull", Toast.LENGTH_LONG)
                    startActivity(Intent(this, DashBoardActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this, "Sign up  not successfull try again later", Toast.LENGTH_LONG)
                }
            }
        }
    }

    private fun redirect() {
        val redirectBTN: TextView = findViewById(R.id.redirect_to_login)
        redirectBTN.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}