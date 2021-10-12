package br.edu.ifsp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.edu.ifsp.Data.DatabaseHelper
import br.edu.ifsp.Model.Contato

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        // Testando DatabaseHelper

        val db = DatabaseHelper(this)

        val c1 = Contato(1,"Fernando", 123, "fpsantis@gmail.com")
        val c2 = Contato(2,"Josi", 321, "pajojane@gmail.com")

        db.inserirContato(c1)
        db.inserirContato(c2)

        val cursor = db.listarContatos()
        cursor.forEach {
            //
        }*/



    }
}