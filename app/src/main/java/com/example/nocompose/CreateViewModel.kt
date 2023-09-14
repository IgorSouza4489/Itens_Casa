package com.example.nocompose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class CreateViewModel : ViewModel() {
    var item = MutableLiveData<modelitem>()
    var status = MutableLiveData<Boolean>()
    var msg = MutableLiveData<String>()



    fun inserir(objeto:String, preco:Int, check:Boolean){
        val item = modelitem(objeto, preco, check)
        val task = composeDao.inserir(item)


        task.addOnSuccessListener {
            msg.value = "Item adicionado!"
            status.value = true
        }.addOnFailureListener{
            msg.value = it.message
        }
    }
}