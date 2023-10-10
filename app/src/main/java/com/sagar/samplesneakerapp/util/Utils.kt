package com.sagar.samplesneakerapp.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> Fragment.collectLatestLifecycleFlow(
    flow: Flow<T>,
    viewLifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.CREATED,
    collect: suspend (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(state) {
            flow.collectLatest(collect)
        }
    }
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun EditText.getTextChangeFlow(): StateFlow<String> {
    val query = MutableStateFlow("")

    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            //No Implementation
        }

        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            //No Implementation
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            text?.let {
                query.value = it.toString()
            }
        }
    })

    return query
}