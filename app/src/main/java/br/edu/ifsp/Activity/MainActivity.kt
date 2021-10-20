package br.edu.ifsp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.Data.ContatoAdapter
import br.edu.ifsp.Data.DatabaseHelper
import br.edu.ifsp.Model.Contato
import br.edu.ifsp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val db = DatabaseHelper(this)
    var listaContatos = ArrayList<Contato>()
    lateinit var adapterContato: ContatoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btNovo = findViewById<FloatingActionButton>(R.id.btnNovoContato)
        btNovo.setOnClickListener {
            val intent = Intent(applicationContext, CadastroActivity::class.java)
            startActivity(intent)
        }

        updateUI()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val item = menu?.findItem(R.id.action_buscar)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterContato?.filter?.filter(newText)
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    fun updateUI() {
        listaContatos = db.listarContatos()
        adapterContato = ContatoAdapter(listaContatos)

        val recyclerview = findViewById<RecyclerView>(R.id.rv_Contatos)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapterContato

        var listener = object : ContatoAdapter.ContatoListener {
            override fun onItemClick(pos: Int) {
                val intent = Intent(applicationContext, DetalheActivity::class.java)
                val c = adapterContato.listaContatos[pos]
                intent.putExtra("contato", c)
                startActivity(intent)
            }
        }
        adapterContato.setClickListener(listener)
    }
}
