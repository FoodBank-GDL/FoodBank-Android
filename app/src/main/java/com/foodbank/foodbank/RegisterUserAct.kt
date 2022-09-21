package com.foodbank.foodbank

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterUserAct : AppCompatActivity() {
    lateinit var registerEmail:EditText
    lateinit var registerPassword:EditText
    lateinit var registerPasswordConfirm:EditText
    lateinit var registerFullName:EditText
    lateinit var registerCellphone:EditText
    lateinit var yaTienesCuentaText: TextView
    lateinit var secondaryRegisterTxt:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user2)
        registerEmail=findViewById(R.id.registerEmailEditText)
        registerPassword=findViewById(R.id.registerPasswordEditText)
        registerPasswordConfirm=findViewById(R.id.registerPasswordConfirmEditText)
        registerFullName=findViewById(R.id.registerNameEditText)
        registerCellphone=findViewById(R.id.registerPhoneEditText)
        yaTienesCuentaText=findViewById(R.id.yaTienesCuentaTextView)
        secondaryRegisterTxt=findViewById(R.id.secondaryRegisterText)

        yaTienesCuentaText.setOnClickListener(View.OnClickListener() {
                Toast.makeText(this, "ESTO TE DEBERIA MANDAR A LA ACTIVIDAD DE LOGIN",
                    Toast.LENGTH_SHORT).show();

        });


    }

    fun registrarUsuario (view: View?) {
        var emailString = registerEmail.text.toString()
        var passwordString = registerPassword.text.toString()
        var passwordConfirmString=registerPasswordConfirm.text.toString()
        if(passwordString==passwordConfirmString){
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

            //Aqui metemos la info a la base de datos

        }else{
            Toast.makeText(this, "WRONG DATA", Toast.LENGTH_SHORT).show()
            secondaryRegisterTxt.text="Las contraseñas ingresadas no coinciden"
            secondaryRegisterTxt.setTextColor(Color.RED)
        }

    }



}