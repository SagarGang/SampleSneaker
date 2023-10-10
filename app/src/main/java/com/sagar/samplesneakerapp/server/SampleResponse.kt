package com.sagar.samplesneakerapp.server

import com.sagar.samplesneakerapp.model.Media
import com.sagar.samplesneakerapp.model.Sneaker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SampleResponse {

    private val cartList: MutableList<Sneaker> = mutableListOf()

    suspend fun getSneakers(): List<Sneaker> = withContext(Dispatchers.Default) {
        val sneakerList = mutableListOf<Sneaker>()
        for (i in 1..50) {
            val product = Sneaker(
                id = "$i",
                brand = "Sneaker $i",
                colorway = "Colorway $i",
                gender = "Gender $i",
                media = Media(
                    thumbUrl = "https://shorturl.at/bhK16"
                ),
                releaseDate = "ReleaseDate $i",
                retailPrice = 5000 + i,
                styleId = "StyleId $i",
                shoe = "Shoe $i",
                name = "Sample Shoe $i",
                title = "Sneaker Name $i",
                year = 2023
            )
            sneakerList.add(product)
        }
        sneakerList
    }


    fun addSneaker(sneaker: Sneaker) {
        cartList.add(sneaker)
    }

    fun getCartSneakers(): List<Sneaker> {
        return cartList
    }

}