package com.newsflow.backend.controller

import com.newsflow.backend.model.Article
import com.newsflow.backend.model.User
import com.newsflow.backend.repository.ArticleRepository
import com.newsflow.backend.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = ["*"])
class NewsController(
    private val userRepository: UserRepository,
    private val articleRepository: ArticleRepository
) {
    // Save user preferences
    @PostMapping("/users/preferences")
    fun savePreferences(@RequestBody user: User): ResponseEntity<User> {
        val saved = userRepository.save(user)
        return ResponseEntity.ok(saved)
    }

    // Get user preferences
    @GetMapping("/users/preferences/{uid}")
    fun getPreferences(@PathVariable uid: String): ResponseEntity<User> {
        val user = userRepository.findById(uid)
        return if (user.isPresent) ResponseEntity.ok(user.get())
        else ResponseEntity.notFound().build()
    }

    // Save favorite article
    @PostMapping("/users/favorites")
    fun saveFavorite(@RequestBody article: Article): ResponseEntity<Article> {
        val saved = articleRepository.save(article)
        return ResponseEntity.ok(saved)
    }

    // Get all favorites
    @GetMapping("/users/favorites/{uid}")
    fun getFavorites(@PathVariable uid: String): ResponseEntity<List<Article>> {
        val favorites = articleRepository.findByFirebaseUid(uid)
        return ResponseEntity.ok(favorites)
    }

    // Remove favorite
    @DeleteMapping("/users/favorites/{uid}")
    fun removeFavorite(
        @PathVariable uid: String,
        @RequestParam url: String
    ): ResponseEntity<Void> {
        articleRepository.deleteByFirebaseUidAndUrl(uid, url)
        return ResponseEntity.ok().build()
    }
}