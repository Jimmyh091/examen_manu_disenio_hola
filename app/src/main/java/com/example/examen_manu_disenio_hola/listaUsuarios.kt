package com.example.examen_manu_disenio_hola

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class listaUsuarios : AppCompatActivity() {

    private lateinit var atras : ImageView

    private lateinit var tietdelete : TextInputEditText
    private lateinit var borrar : Button

    private lateinit var listUsuarios : TextView

    private lateinit var intentatras : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_usuarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var usuario = intent.getStringArrayListExtra("usuario")

        listUsuarios = findViewById(R.id.listUsuarios)
        listUsuarios.text = ""

        for (it in usuario!!){
            listUsuarios.setText( listUsuarios.text.toString() + "Nombre: ${it.get(0)}, Email: ${it.get(2)}\n\n")
        }

        intentatras = Intent(this, MainActivity::class.java)

        atras = findViewById(R.id.atraslista)
        atras.setOnClickListener {
            startActivity(intentatras)
        }

        atras = findViewById(R.id.atraslista)

        tietdelete = findViewById(R.id.tiet1lista)
        borrar = findViewById(R.id.borrar)

    }
}