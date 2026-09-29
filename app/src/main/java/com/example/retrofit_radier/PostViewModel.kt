package com.example.retrofit_radier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_radier.network.Post
import com.example.retrofit_radier.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                // A mágica acontece aqui, de forma assíncrona!
                val response = RetrofitClient.apiService.getPosts()
                _posts.value = response
            } catch (e: Exception) {
                // Em um app real, trataríamos o erro aqui
                e.printStackTrace()
            }
        }
    }
}
