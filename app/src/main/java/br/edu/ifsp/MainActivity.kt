package br.edu.ifsp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.Data.ContatoAdapter
import br.edu.ifsp.Data.DatabaseHelper
import br.edu.ifsp.Model.Contato

class MainActivity : AppCompatActivity() {

    val db = DatabaseHelper(this)
    var listaContatos = ArrayList<Contato>()
    var adapterContato: ContatoAdapter?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val c1 = Contato(1,"Fernando", "123", "fpsantis@gmail.com")
        val c2 = Contato(2,"Josi", "321", "pajojane@gmail.com")
        db.inserirContato(c1)
        db.inserirContato(c2)


        updateUI()

    }

    fun updateUI()
    {
        listaContatos = db.listarContatos()
        adapterContato = ContatoAdapter(listaContatos)

        val recyclerview = findViewById<RecyclerView>(R.id.rv_Contatos)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapterContato

    }

}