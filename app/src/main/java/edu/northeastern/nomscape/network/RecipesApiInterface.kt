package edu.northeastern.nomscape.network

import edu.northeastern.nomscape.models.Response
import retrofit2.http.GET

interface RecipesApiInterface {
    @GET("list?from=0&size=20&tags=under_30_minutes")
    suspend fun getRecipes(): Response
}