package com.betrybe.sociallogin

import android.os.Bundle
import android.widget.Button
import android.text.TextWatcher
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private val loginInputLayout by lazy { findViewById<TextInputLayout>(R.id.email_text_input_layout) }
    private val passwordInputLayout by lazy { findViewById<TextInputLayout>(R.id.password_text_input_layout) }
    private val loginButton by lazy { findViewById<Button>(R.id.login_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener{
            val email = loginInputLayout.editText?.text.toString()
            val isEmailValid = isEmailValid(email)
            val pass = passwordInputLayout.editText?.text.toString()

            if(pass.length < 4){
                passwordInputLayout.error = "Senha deve ter mais de 4 caracteres"
            }else{
                passwordInputLayout.error = null
            }

            if(!isEmailValid){
                loginInputLayout.error = "Email inválido"
            }else{
                loginInputLayout.error = null
            }
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validate()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
        }
        loginInputLayout.editText?.addTextChangedListener(textWatcher)
        passwordInputLayout.editText?.addTextChangedListener(textWatcher)

    }
    private fun isEmailValid(email: String): Boolean {
        val emailRegex = ("(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
        return email.matches(emailRegex.toRegex())
    }
    private fun validate() {
        val email = loginInputLayout.editText?.text.toString()
        val password = passwordInputLayout.editText?.text.toString()

        loginButton.isEnabled =  password.isNotBlank() && email.isNotBlank()
    }

}
