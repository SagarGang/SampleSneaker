package com.sagar.samplesneakerapp.repo

import com.sagar.samplesneakerapp.model.Sneaker


interface SneakerRepository {

    suspend fun getSneakers()
    suspend fun addSneaker(sneaker: Sneaker)
    suspend fun getCartSneakers()
}