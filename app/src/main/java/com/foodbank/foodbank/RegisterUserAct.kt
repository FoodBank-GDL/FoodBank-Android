package com.foodbank.foodbank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterUserAct : AppCompatActivity() {
    lateinit var registerEmail:EditText
    lateinit var registerPassword:EditText
    lateinit var registerPasswordConfirm:EditText
    lateinit var registerFullName:EditText
    lateinit var registerCellphone:EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user2)
        registerEmail=findViewById(R.id.registerEmailEditText)
        registerPassword=findViewById(R.id.registerPasswordEditText)
        registerPasswordConfirm=findViewById(R.id.registerPasswordConfirmEditText)
        registerFullName=findViewById(R.id.registerNameEditText)
        registerCellphone=findViewById(R.id.registerPhoneEditText)


    }

    fun registrarUsuario (view: View?) {
        var emailString = registerEmail.text.toString()
        var passwordString = registerPassword.text.toString()
        var passwordConfirmString=registerPasswordConfirm.text.toString()

        var authTask = Firebase.auth.createUserWithEmailAndPassword(emailString, passwordString)

        authTask.addOnCompleteListener(this)
        { resultado ->
            if (resultado.isSuccessful)
            {
                Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show()
            } else
            {
                Toast.makeText(this, "ERROR EN REGISTRO", Toast.LENGTH_SHORT).show()
                Log.wtf("FIREBASE-DEV", "error: ${resultado.exception}")

            }
        }
    }

}