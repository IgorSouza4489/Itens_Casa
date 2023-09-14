package com.example.nocompose

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class composeDao {

    companion object {
        private val collection = Firebase.firestore.collection("lista")


        fun inserir(modelitem: modelitem) = collection.add(modelitem)

        fun listar() = collection
    }
}