package com.example.nocompose

import com.google.firebase.firestore.DocumentId

class modelitem(
    val objeto: String? = null,
    val preco: Int? = null,
    var check: Boolean? = null,

    @DocumentId
    val documentId: String? = null
) {

}