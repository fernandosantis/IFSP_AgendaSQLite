package br.edu.ifsp.Activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.Data.DatabaseHelper
import br.edu.ifsp.Model.Contato
import br.edu.ifsp.R

class DetalheActivity : AppCompatActivity() {

    private var contato = Contato()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        contato = this.intent.getSerializableExtra("contato") as Contato
        val nome = findViewById<EditText>(R.id.et_Nome)
        val tel = findViewById<EditText>(R.id.et_Tel)
        val email = findViewById<EditText>(R.id.et_Email)

        nome.setText(contato.nome)
        tel.setText(contato.fone)
        email.setText(contato.email)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhe, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val db = DatabaseHelper(this)
        if (item.itemId == R.id.action_editar) {
            val nome = findViewById<EditText>(R.id.et_Nome).text.toString()
            val tel = findViewById<EditText>(R.id.et_Tel).text.toString()
            val email = findViewById<EditText>(R.id.et_Email).text.toString()
            contato.nome = nome
            contato.fone = tel
            contato.email = email

            if (db.atualizarContato(contato)> 0) {
                Toast.makeText(this, "Informações alteradas", Toast.LENGTH_LONG).show()
            }
        }

        if (item.itemId == R.id.action_deletar) {
            if (db.apagarContato(contato)> 0) {
                Toast.makeText(this, "Contato Excluido", Toast.LENGTH_LONG).show()
            }
        }
        finish()
        return super.onOptionsItemSelected(item)
    }
}
