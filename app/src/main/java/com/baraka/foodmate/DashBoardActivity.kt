package com.baraka.foodmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.w3c.dom.Text

class DashBoardActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference?.child("profile")
        Toast.makeText(
            this,
            "Success!",
            Toast.LENGTH_LONG
        ).show()
        getProfile()
        logOut()
    }

private  fun getProfile(){
    val user = auth.currentUser;

    val firstName:TextView = findViewById(R.id.first_name_text)
    val lastName: TextView = findViewById(R.id.last_name_text)
    var userReference = databaseReference?.child(user?.uid!!)

    userReference?.addValueEventListener(object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            firstName.text = snapshot.child("firstname").value.toString()
            lastName.text = snapshot.child("lastname").value.toString()
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    })
}

    private fun logOut(){
        val logOutButton: Button = findViewById(R.id.log_out);
        logOutButton.setOnClickListener{
            auth.signOut();
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}