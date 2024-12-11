package com.example.examen_manu_disenio_hola

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class crearCuenta : AppCompatActivity() {

    private lateinit var tietnombre : TextInputEditText
    private lateinit var tietpass : TextInputEditText
    private lateinit var tietcorreo : TextInputEditText

    private lateinit var atras : ImageView
    private lateinit var botonguardar : Button

    private lateinit var intentatras : Intent
    private lateinit var intentguardar : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_cuenta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        intentatras = Intent(this, MainActivity::class.java)
        intentguardar = Intent(this, MainActivity::class.java)

        tietnombre = findViewById(R.id.tiet1crear)
        tietpass = findViewById(R.id.tiet2crear)
        tietcorreo = findViewById(R.id.tiet3crear)

        atras = findViewById(R.id.atrascrear)
        atras.setOnClickListener {
            startActivity(intentatras)
        }

        botonguardar = findViewById(R.id.guardar)

        botonguardar.setOnClickListener {
            val nombre = comprobarNombre()
            val pass = comprobarPass()
            val correo = comprobarCorreo()

            if (nombre && pass && correo){
                tietnombre.error = null
                tietpass.error = null
                tietcorreo.error = null

                intentguardar.putExtra("nombre", tietnombre.text.toString())
                intentguardar.putExtra("pass", tietpass.text.toString())
                intentguardar.putExtra("correo", tietcorreo.text.toString())

                val toastcrear = Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show()

                startActivity(intentguardar)
            }else{
                if (!nombre) tietnombre.error = "Nombre muy largo"
                if (!pass) tietpass.error = "Contraseña insegura"
                if (!correo) tietcorreo.error = "Correo con mala sintaxis"
            }
        }

    }

    fun comprobarNombre() : Boolean{
        val nombre = tietnombre.text.toString()
        return nombre.length <= 16
    }

    fun comprobarPass() : Boolean{
        var pass = tietpass.text.toString()

        var tieneMayuscula = false
        var tieneMinuscula = false
        var tieneLongitud = tietpass.text.toString().length > 8

        for (it in pass){
            if (it.isLetter()){
                if (it.isUpperCase()){
                    tieneMayuscula = true
                }else{
                    tieneMinuscula = true
                }
            }
        }

        return tieneMayuscula && tieneMinuscula && tieneLongitud
    }

    fun comprobarCorreo() : Boolean{
        var correo = tietcorreo.text.toString()

        var correoAux = correo.replace("@hotmail.","").replace("@gmail.","")

        return correo.length - 9 == correoAux.length || correo.length - 7 == correoAux.length
    }
}