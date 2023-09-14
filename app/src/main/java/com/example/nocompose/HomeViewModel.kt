package com.example.nocompose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {
    var itens = MutableLiveData<List<modelitem>>()

    var msg = MutableLiveData<String>()

    fun atualizarValorNoFirebase(itemID: String, novoValor: Boolean) {
        // Atualize o valor no Firebase com base no itemID e novoValor
        // Substitua isso com a lógica específica do Firebase para o seu aplicativo
        val db = FirebaseFirestore.getInstance()
        val documentReference = db.collection("lista").document(itemID)

        val data = hashMapOf(
            "check" to novoValor
        )

        documentReference
            .update(data as Map<String, Any>)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }
    }
    init {
        composeDao.listar()
            .addSnapshotListener{snapshot, error ->
                if (error != null){
                    msg.value = error.message
                }

                if (snapshot != null){
                    itens.value = snapshot.toObjects(modelitem::class.java)
                }
            }
    }

}