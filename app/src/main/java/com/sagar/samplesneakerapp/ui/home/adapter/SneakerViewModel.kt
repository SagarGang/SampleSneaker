package com.sagar.samplesneakerapp.ui.home.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagar.samplesneakerapp.model.Sneaker
import com.sagar.samplesneakerapp.repo.SneakerRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val DELAY = 400L

@HiltViewModel
class SneakerViewModel @Inject constructor(private val repository: SneakerRepositoryImpl) :
    ViewModel() {

    private val _sneakerList = MutableStateFlow<List<Sneaker>>(emptyList())
    val sneakerList: MutableStateFlow<List<Sneaker>>
        get() = _sneakerList

    val sneakerAdded: StateFlow<Boolean>
        get() = repository.addSneaker

    val cartList: StateFlow<List<Sneaker>>
        get() = repository.cartList

    private val _searchQuery = MutableStateFlow("")
    var searchQuery: StateFlow<String>
        get() = _searchQuery
        set(value) {
            _searchQuery.value = value.toString()
        }

    fun getSneakers() {
        viewModelScope.launch(Dispatchers.IO){
            repository.getSneakers().collect{
                withContext(Dispatchers.Main.immediate){
                    _sneakerList.emit(it)
                }
            }
        }
    }

    fun addToCart(sneaker: Sneaker) {
        viewModelScope.launch {
            repository.addSneaker(sneaker)
        }
    }

    fun getCartList() {
        viewModelScope.launch {
            repository.getCartSneakers()
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun observeSearch(queryFlow: StateFlow<String>) {
        viewModelScope.launch {
            queryFlow
                .debounce(DELAY)
                .distinctUntilChanged()
                .flatMapLatest { query -> flowOf(query) }
                .collect { result ->
                    updateResult(result)
                }
        }
    }

    private fun updateResult(result: String) {
        _searchQuery.value = result
    }

}