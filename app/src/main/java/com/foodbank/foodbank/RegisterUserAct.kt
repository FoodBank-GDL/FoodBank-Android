package com.foodbank.foodbank

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterUserAct : AppCompatActivity() {
    lateinit var registerEmail:EditText
    lateinit var registerPassword:EditText
    lateinit var registerPasswordConfirm:EditText
    lateinit var registerFullName:EditText
    lateinit var registerCellphone:EditText
    lateinit var yaTienesCuentaText: TextView
    lateinit var secondaryRegisterTxt:TextView
    lateinit var registerButton : Button


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
        registerButton = findViewById(R.id.registerBtn)
        yaTienesCuentaText.setOnClickListener(View.OnClickListener() {
                Toast.makeText(this, "ESTO TE DEBERIA MANDAR A LA ACTIVIDAD DE LOGIN",
                    Toast.LENGTH_SHORT).show();

        });

        registerPasswordConfirm.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var passwordString = registerPassword.text.toString()
                var passwordConfirmString=registerPasswordConfirm.text.toString()
                if (passwordString == passwordConfirmString){
                    registerButton.isEnabled = true
                }else{
                    registerButton.isEnabled = false
                }
            }
        })


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
                    //Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show()
                    val nuevoUsuario= hashMapOf("nombre" to registerFullName.text.toString(),
                        "telefono" to registerCellphone.text.toString(),
                        "email" to registerEmail.text.toString())

                    val coleccion: CollectionReference =
                        Firebase.firestore.collection("usuarios")

                    val taskAdd=coleccion.add(nuevoUsuario)
                    taskAdd.addOnSuccessListener { doc->
                        //Toast.makeText(this, "id: ${doc.id}", Toast.LENGTH_SHORT).show()

                        //Aqui es donde ya se hizo todo al 100 y nos podemos ir al login

                    }.addOnFailureListener{error ->
                        //Toast.makeText(this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_SHORT).show()
                        Log.e("Firestore","error $error")

                    }

                } else
                {
                    //Toast.makeText(this, "ERROR EN REGISTRO", Toast.LENGTH_SHORT).show()
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