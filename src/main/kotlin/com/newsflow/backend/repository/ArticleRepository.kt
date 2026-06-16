package com.newsflow.backend.repository

import com.newsflow.backend.model.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Long> {
    fun findByFirebaseUid(firebaseUid: String): List<Article>
    fun deleteByFirebaseUidAndUrl(firebaseUid: String, url: String)
}