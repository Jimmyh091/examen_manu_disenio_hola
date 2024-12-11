package com.example.examen_manu_disenio_hola

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private var listUsuarios : MutableList<ArrayList<String?>> = mutableListOf()

    private lateinit var tietuser : TextInputEditText
    private lateinit var tietpass : TextInputEditText

    private lateinit var crearCuentaText: TextView
    private lateinit var botonEntrar : Button

    private lateinit var crearCuentaIntent : Intent
    private lateinit var entrarIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = intent.getStringExtra("nombre")
        val pass = intent.getStringExtra("pass")
        val correo = intent.getStringExtra("correo")

        listUsuarios.add(arrayListOf(user, pass, correo))

        crearCuentaIntent = Intent(this, crearCuenta::class.java)
        entrarIntent = Intent(this, listaUsuarios::class.java)

        tietuser = findViewById(R.id.tiet1main)
        tietpass = findViewById(R.id.tiet2main)

        crearCuentaText = findViewById(R.id.crearcuenta)
        botonEntrar = findViewById(R.id.botonentrar)

        crearCuentaText.setOnClickListener {
            startActivity(crearCuentaIntent)
        }

        botonEntrar.setOnClickListener {

            for (it in listUsuarios){
                if (it[0] == tietuser.text.toString() && it[1] == tietpass.text.toString()){

                    for (it2 in listUsuarios){
                        entrarIntent.putStringArrayListExtra("usuario", it2)
                    }

                    startActivity(entrarIntent)
                }
            }

        }
    }
}