package com.baraka.foodmate

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_settings.*
import java.io.ByteArrayOutputStream


class Settings : Fragment() {
    private  lateinit var imageUri: Uri
    private  val REQUEST_IMAGE_CAPTURE = 100
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    //get the current logged in user
    private val currentUser = FirebaseAuth.getInstance().currentUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference?.child("profile")
        currentUser?.let{user ->
            setUserFullNameAndEmail()
        }


        profile_image_view.setOnClickListener{
            takePictureIntent()
        }
    }

    private fun takePictureIntent(){
        //open default camera application
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            pictureIntent ->
            pictureIntent.resolveActivity(activity?.packageManager!!)?.also {
                //REQ H
                startActivityForResult(pictureIntent, REQUEST_IMAGE_CAPTURE)
            }
                   }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            uploadImageAndSaveURI(imageBitmap)
        }

    }

    private fun uploadImageAndSaveURI(bitmap: Bitmap){
        //byte array output stream
        val baos = ByteArrayOutputStream()
        val storageRef = FirebaseStorage.getInstance().reference
            .child("profile_pics/${FirebaseAuth.getInstance().currentUser?.uid}")
        //Quality of compression 100 best 0 is worst
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image  = baos.toByteArray()
        val upload = storageRef.putBytes(image)

        progress_circular.visibility = View.VISIBLE
        upload.addOnCompleteListener{ uploadTask ->
            progress_circular.visibility = View.INVISIBLE
            if(uploadTask.isSuccessful){
                storageRef.downloadUrl.addOnCompleteListener{
                    downloadTask->
                    downloadTask.result?.let {
                        uri ->
                        imageUri = uri
                        profile_image_view.setImageBitmap(bitmap)
                    }
                }
            }else{
                uploadTask.exception?.let{exception ->
                    Toast.makeText(context, exception.message, Toast.LENGTH_LONG)
                }
            }
        }

    }

    private  fun setUserFullNameAndEmail(){
        var userReference = databaseReference?.child(currentUser?.uid!!)

        userReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user_first_name.setText(snapshot.child("firstname").value.toString())
                user_last_name.setText(snapshot.child("lastname").value.toString())
                user_email.setText(currentUser?.email)
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}