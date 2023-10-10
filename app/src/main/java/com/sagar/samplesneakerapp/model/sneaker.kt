package com.sagar.samplesneakerapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sneaker(
    val id: String? = null,
    val brand: String? = null,
    val colorway: String? = null,
    val gender: String? = null,
    val media: Media? = Media(),
    val releaseDate: String? = null,
    val retailPrice: Int? = null,
    val styleId: String? = null,
    val shoe: String? = null,
    val name: String? = null,
    val title: String? = null,
    val year: Int? = null,
    val sizes: List<Size> = listOf()
) : Parcelable

@Parcelize
data class Media(
    var thumbUrl: String? = null
) : Parcelable

@Parcelize
data class Size(
    var size: Int = 0
) : Parcelable
