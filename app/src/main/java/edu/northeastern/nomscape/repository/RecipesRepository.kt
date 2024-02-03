package edu.northeastern.nomscape.repository

import edu.northeastern.nomscape.models.Recipe
import edu.northeastern.nomscape.network.RecipesApiClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecipesRepository {

    fun fetchRecipes(resultCallback: (List<Recipe>) -> Unit) {
        GlobalScope.launch {
            val result = RecipesApiClient.getRecipesClient().getRecipes().results
            resultCallback.invoke(result)
        }
    }

    suspend fun fetchRecipeDetail() {

    }
}