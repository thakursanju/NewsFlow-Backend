package com.newsflow.backend.repository

import com.newsflow.backend.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>