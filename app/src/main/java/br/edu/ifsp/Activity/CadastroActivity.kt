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

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cadastro, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val db = DatabaseHelper(this)

        if (item.itemId == R.id.action_salvar) {

            val nome = findViewById<EditText>(R.id.et_Nome).text.toString()
            val fone = findViewById<EditText>(R.id.et_Tel).text.toString()
            val email = findViewById<EditText>(R.id.et_Email).text.toString()

            val ctato = Contato(null, nome, fone, email)
            if (db.inserirContato(ctato) > 0) {
                Toast.makeText(this, "Contato Inserido", Toast.LENGTH_LONG).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
