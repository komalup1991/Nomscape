package edu.northeastern.nomscape.repository

import edu.northeastern.nomscape.models.Results
import edu.northeastern.nomscape.network.RecipesApiClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecipesRepository {

    val resultList = listOf(
        Results(
            id = "4704",
            servings = "4",
            prepTimeMinutes = "5",
            name = "Low-Carb Avocado Chicken Salad",
            thumbnailUrl = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/45b4efeb5d2c4d29970344ae165615ab/FixedFBFinal.jpg",
            description = "This chicken salad is a lunchtime delight! Packed with creamy avocado, tender chicken, and crunchy veggies, it's a healthy and satisfying meal that won't weigh you down. Tossed in a tangy yogurt dressing with a hint of spice, it's a flavor explosion that's perfect for a light meal."
        ),
        Results(
            id = "5466",
            servings = "4",
            prepTimeMinutes = "5",
            name = "Tomato And Anchovy Pasta",
            thumbnailUrl = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/216182.jpg",
            description = "Savor the bold flavors of this Tomato and Anchovy Pasta, a perfect weeknight meal that's both simple and satisfying. With a zesty tomato sauce and umami-packed anchovies, this dish will have your taste buds dancing in no time!"
        ),
        Results(
            id = "5814",
            servings = "4",
            prepTimeMinutes = "5",
            name = "Blueberry Cream Muffins",
            thumbnailUrl = "https://img.buzzfeed.com/tasty-app-user-assets-prod-us-east-1/recipes/4e9524578f544c888af761e10630593b.jpeg",
            description = "Yummy blueberry muffins."
        )
    )

    fun fetchRecipes(resultCallback: (List<Results>) -> Unit) {
        GlobalScope.launch {
            val result = RecipesApiClient.getRecipesClient().getRecipes().results
            resultCallback.invoke(result)
        }
    }

    suspend fun fetchRecipeDetail() {

    }
}