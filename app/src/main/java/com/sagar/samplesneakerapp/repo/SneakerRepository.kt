package com.sagar.samplesneakerapp.repo

import com.sagar.samplesneakerapp.model.Sneaker
import kotlinx.coroutines.flow.Flow


interface SneakerRepository {

    suspend fun getSneakers(): Flow<List<Sneaker>>
    suspend fun addSneaker(sneaker: Sneaker)
    suspend fun getCartSneakers()
}