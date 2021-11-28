package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignIn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextEmail = findViewById(R.id.et_Email)
        editTextPassword = findViewById(R.id.et_Password)
        buttonSignIn = findViewById(R.id.btnSignin)
        buttonSignIn.setOnClickListener(View.OnClickListener {
            if (editTextPassword.text.toString().isNotBlank() && editTextEmail.text.toString().isNotBlank()){
                if (editTextEmail.text.toString().contains('@')){
                    val intent = Intent(this, PictureSearchActivity::class.java)
                    startActivity(intent)
                } else {
                    val toast = Toast.makeText(applicationContext, "Введите существующий email", Toast.LENGTH_SHORT)
                    toast.show()
                }
            } else{
                val toast = Toast.makeText(applicationContext, "Все поля должны быть заполнены", Toast.LENGTH_SHORT)
                toast.show()
            }
        })
    }
}