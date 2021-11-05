package br.edu.ifsp.Data

import android.content.Context
import android.net.Uri
import android.provider.BaseColumns._ID
import android.provider.ContactsContract
import br.edu.ifsp.Model.Contato

class ContentProviderHelper(contexto: Context) {
    private val contentResolver = contexto.contentResolver
    companion object {
        private val CONTATOS_CONTENT_PROVIDER_URI = Uri.parse("content://com.android.contacts/contacts/")
        private val TELEFONES_CONTENT_PROVIDER_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        private val EMAILS_CONTENT_PROVIDER_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI
    }

    fun inserirContato(contato: Contato): Long {
        TODO("NÃO VAMOS TRADABALHAR NESTE EXEMPLO")
    }

    fun atualizarContato(contato: Contato): Int {
        TODO("NÃO VAMOS TRADABALHAR NESTE EXEMPLO")
    }

    fun apagarContato(contato: Contato): Int {
        TODO("NÃO VAMOS TRADABALHAR NESTE EXEMPLO")
    }

    private fun recuperarPrimeiroTelefoneOuEmail(id: Long, tipo: Boolean): String { // tipo Telefone = True, Email = false
        val uri: Uri
        val coluna: String
        val chaveContato: String
        if (tipo == true) {
            uri = TELEFONES_CONTENT_PROVIDER_URI
            coluna = ContactsContract.CommonDataKinds.Phone.NUMBER
            chaveContato = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
        } else {
            uri = EMAILS_CONTENT_PROVIDER_URI
            coluna = ContactsContract.CommonDataKinds.Email.ADDRESS
            chaveContato = ContactsContract.CommonDataKinds.Email.CONTACT_ID
        }
        val cursor = contentResolver.query(uri, arrayOf(coluna), "$chaveContato = ?", arrayOf(id.toString()), null)
        val retorno = if (cursor != null && cursor.moveToFirst()) {
            cursor.getString(cursor.getColumnIndexOrThrow(coluna)) ?: ""
        } else {
            ""
        }
        cursor?.close()
        return retorno
    }

    fun listarContatos(): ArrayList<Contato> {
        val listaContatos = ArrayList<Contato>()
        val cursor = contentResolver.query(CONTATOS_CONTENT_PROVIDER_URI, null, null, null, null)
        cursor?.apply {
            while (this.moveToNext()) {
                with(this) {
                    val id = getLong(getColumnIndexOrThrow(_ID))
                    val c = Contato(
                        id.toInt(),
                        getString(getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)),
                        recuperarPrimeiroTelefoneOuEmail(id, true),
                        recuperarPrimeiroTelefoneOuEmail(id, false)
                    )
                    with (c) {
                        if (fone.isNotBlank() && fone.isNotEmpty() && email.isNotBlank() && email.isNotEmpty()) {
                            listaContatos.add(c)
                        }
                    }
                }
            }
        }
        return listaContatos
    }
}
