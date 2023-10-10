package com.sagar.samplesneakerapp.repo

import com.sagar.samplesneakerapp.di.SneakerApi
import com.sagar.samplesneakerapp.model.Sneaker
import com.sagar.samplesneakerapp.server.SampleResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SneakerRepositoryImpl @Inject constructor(private val sneakerApi: SneakerApi) :
    SneakerRepository {

    private val _sneakerList = MutableStateFlow<List<Sneaker>>(emptyList())
    val sneakerList: MutableStateFlow<List<Sneaker>>
        get() = _sneakerList

    private val _addSneaker = MutableStateFlow(false)
    val addSneaker: StateFlow<Boolean>
        get() = _addSneaker

    private val _cartList = MutableStateFlow<List<Sneaker>>(emptyList())
    val cartList: MutableStateFlow<List<Sneaker>>
        get() = _cartList

    override suspend fun getSneakers() {
        _sneakerList.emit(SampleResponse.getSneakers())
    }

    override suspend fun addSneaker(sneaker: Sneaker) {
        SampleResponse.addSneaker(sneaker)
        _addSneaker.emit(true)
    }

    override suspend fun getCartSneakers() {
        _cartList.emit(SampleResponse.getCartSneakers())
    }

}