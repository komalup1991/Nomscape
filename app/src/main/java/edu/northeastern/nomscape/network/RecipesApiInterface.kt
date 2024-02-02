package edu.northeastern.nomscape.network

import edu.northeastern.nomscape.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApiInterface {
    @GET("list?from=0&size=20&tags=under_30_minutes")
    suspend fun getRecipes(): Response

    @GET("get-more-info")
    suspend fun getRecipeDetails(@Query("id") id: String): Response

}