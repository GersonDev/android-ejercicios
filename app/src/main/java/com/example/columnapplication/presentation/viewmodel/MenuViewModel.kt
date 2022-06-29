package com.example.columnapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.columnapplication.domain.models.Card
import com.example.columnapplication.domain.models.Person
import com.example.columnapplication.domain.repositories.CardsReporitory
import com.example.columnapplication.domain.repositories.PersonsRepository

class MenuViewModel : ViewModel() {

    private val _messageNombre = MutableLiveData("")
    val messageNombre: LiveData<String> = _messageNombre
    private val _messageApellido = MutableLiveData("")
    val messageApellido: LiveData<String> = _messageApellido
    private val _messageDni = MutableLiveData("")
    val messageDni: LiveData<String> = _messageDni
    private val _messageMonto = MutableLiveData("")
    val messageMonto: LiveData<String> = _messageMonto
    private val _listaDeRegistro=MutableLiveData<List<Card>>()
    val  listaDeRegistro:LiveData<List<Card>> = _listaDeRegistro

    private val cardsReporitory = CardsReporitory()
    private val personsRepository = PersonsRepository()

    fun mostarTituloDelProyecto(): String {
        return "Tren Electrico Linea 1"
    }

    fun mostrarSubtitulosDelProyecto1(): String {
        return ("1.Venta de Tarjetas")

    }

    fun mostrarSubtitulosDelProyecto2(): String {
        return ("2.Ver estado de cuenta")
    }

    fun mostrarSubtitulosDelProyecto3(): String {
        return ("3.Escanear Tarjeta para ingresar al tren")
    }

    fun mostrarSubtitulosDelProyecto4(): String {
        return ("4.Recargar Tarjeta")
    }

    fun mostrarSubtitulosDelProyecto5(): String {
        return ("5.Exit")
    }

    fun enviarNombre(nombre: String) {
        _messageNombre.value = nombre

    }

    fun enviarApellido(apellido: String) {
        _messageApellido.value = apellido
    }

    fun enviarDni(dni: String) {
        _messageDni.value = dni
    }

    fun enviarMonto(amount: String) {
        _messageMonto.value = amount
    }

    fun registerPerson() {
        val monto = _messageMonto.value?.toDouble() ?: 0.0
        if (monto >= 10.0) {
            val card = cardsReporitory.popFirstCard()
            card?.balance = monto
            val person = Person(
                name = _messageNombre.value ?: "",
                apellido = _messageApellido.value ?: "",
                dni = _messageDni.value?.toInt() ?: 0
            )
            person.card = card
            println("Su tarjeta se genero correctamente")
            personsRepository.addPersonQueue(person)
        } else {
            println("Su monto es inferior a 10.00 soles")
        }
    }

    fun actualizarTarjetas() {
        val tarjetas=cardsReporitory.obtenerTarjetas()
        _listaDeRegistro.value=tarjetas
    }

    fun passCard() {
        val person = personsRepository.sacarPersonQueue()
        person!!.card!!.balance -= 20.00
        println("Su pase fue exitoso")
        personsRepository.addPersonQueue(person!!)
    }

    fun rechargedCard(amount: Double) {
        val person = personsRepository.sacarPersonQueue()
        person!!.card!!.balance += amount
        println("Su recarga se completo exitosamente")
        personsRepository.addPersonQueue(person!!)
    }
}