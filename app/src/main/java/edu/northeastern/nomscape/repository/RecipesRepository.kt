package edu.northeastern.nomscape.repository

import edu.northeastern.nomscape.network.RecipesApiClient

class RecipesRepository {

    suspend fun fetchRecipes() = RecipesApiClient.getRecipesClient().getRecipes()
}