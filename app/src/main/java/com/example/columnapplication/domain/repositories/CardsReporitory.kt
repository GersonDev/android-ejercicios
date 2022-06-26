package com.example.columnapplication.domain.repositories

import com.example.columnapplication.domain.models.Card
import com.example.columnapplication.utils.datastructure.Stack


class CardsReporitory {
    private val cards: Stack<Card> = Stack()

    init {
        cards.push(Card("A1", 10.00))
        cards.push(Card("A2", 10.00))
        cards.push(Card("A3", 10.00))
        cards.push(Card("A4", 10.00))
        cards.push(Card("A5", 10.00))
        cards.push(Card("A6", 10.00))
        cards.push(Card("A7", 10.00))
        cards.push(Card("A8", 10.00))
        cards.push(Card("A9", 10.00))
    }

    fun popFirstCard(): Card? {
        return cards.pop()
    }

    fun printCard() {
        println(cards.toString())
    }
}