package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignin.setOnClickListener{
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()
            if (checkEmail(email)) {
                if (checkPassword(password)){
                    val intent = Intent(this, PictureSearchActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        binding.etEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT){
                return@setOnEditorActionListener !checkEmail(binding.etEmail.text.toString())
            }
            return@setOnEditorActionListener false
        }
        binding.etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                return@setOnEditorActionListener !checkPassword(binding.etPassword.text.toString())
            }
            return@setOnEditorActionListener false
        }

        binding.etEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.emailInputLayout.isErrorEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.etPassword.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.passwordInputLayout.isErrorEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }


    private fun checkEmail(email: String): Boolean{
        return if (email.isNotBlank() && email.count { ch -> ch == '@'} == 1 &&
            email.substringBefore('@').substringBefore('+').trimMargin(".").length >= 2 &&
            email.substringAfter('@').count{ch -> ch == '.'} == 1 &&
            email.substringAfter('@').substringBefore('.').isNotBlank() &&
            email.substringAfterLast('.').isNotBlank()){
            true
        } else{
            binding.emailInputLayout.error = "Введите корректный email"
            false
        }
    }

    private fun checkPassword(password: String): Boolean{
        if (password.length >= 8){
            if (password.indexOfAny(charArrayOf('!','@','#','$','%','^','&','*')) >= 0){
                if (password.indexOfAny(charArrayOf('0','1','2','3','4','5','6','7','8','9')) >= 0){
                    return true
                } else{
                    binding.passwordInputLayout.error = "Пароль должен содержать хотя бы одно число"
                }
            } else{
                binding.passwordInputLayout.error = "Пароль должен содержать хотя бы один спец. символ (!@#$%^&*)"
            }
        } else{
            binding.passwordInputLayout.error = "Пароль должен быть не короче 8 символов"
        }
        return false
    }
}