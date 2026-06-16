package com.newsflow.backend.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    val firebaseUid: String = "",
    val email: String = "",
    val country: String = "us",
    val language: String = "en",
    val darkMode: Boolean = false
)