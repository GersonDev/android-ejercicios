package com.example.columnapplication.domain.repositories

import com.example.columnapplication.domain.models.Person
import com.example.columnapplication.utils.datastructure.Queue

class PersonsRepository {
    private val persons: Queue<Person> = Queue()

    fun addPersonQueue(person: Person) {
        persons.enqueue(person)
    }

    fun printQueue() {
        println(persons.toString())
    }

    fun sacarPersonQueue(): Person? {
        return persons.dequeue()
    }
}