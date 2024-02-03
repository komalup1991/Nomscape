package edu.northeastern.nomscape.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import edu.northeastern.nomscape.models.Recipe
import edu.northeastern.nomscape.repository.RecipesRepository
import edu.northeastern.nomscape.ui.theme.NomscapeTheme

class MainActivity : ComponentActivity() {

    val resultList = listOf(
        Recipe(
            id = "4704",
            servings = "4",
            prepTimeMinutes = "5",
            name = "Low-Carb Avocado Chicken",
            thumbnailUrl = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/45b4efeb5d2c4d29970344ae165615ab/FixedFBFinal.jpg",
            description = "This chicken salad is a lunchtime delight! Packed with creamy avocado, tender chicken, and crunchy veggies, it's a healthy and satisfying meal that won't weigh you down. Tossed in a tangy yogurt dressing with a hint of spice, it's a flavor explosion that's perfect for a light meal."
        ),
        Recipe(
            id = "5466",
            servings = "4",
            prepTimeMinutes = "5",
            name = "Tomato And Anchovy Pasta",
            thumbnailUrl = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/216182.jpg",
            description = "Savor the bold flavors of this Tomato and Anchovy Pasta, a perfect weeknight meal that's both simple and satisfying. With a zesty tomato sauce and umami-packed anchovies, this dish will have your taste buds dancing in no time!"
        ),
        Recipe(
            id = "5814",
            servings = "4",
            prepTimeMinutes = "5",
            name = "Blueberry Cream Muffins",
            thumbnailUrl = "https://img.buzzfeed.com/tasty-app-user-assets-prod-us-east-1/recipes/4e9524578f544c888af761e10630593b.jpeg",
            description = "Yummy blueberry muffins."
        )
    )

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            NomscapeTheme {
                Scaffold(topBar = {
                    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ), title = { Text(text = "Recipes") })
                }, containerColor = MaterialTheme.colorScheme.outlineVariant) { paddingValues ->
                    Row(modifier = Modifier.padding(paddingValues)) {
                        var recipeList by remember {
                            mutableStateOf(listOf<Recipe>())
                        }

                        RecipesRepository().fetchRecipes { resultsList ->
                            recipeList = resultsList
                        }
                        RecipeList(recipeList = recipeList, context = LocalContext.current)
                    }
                }
            }
        }
    }
}

val resultList = listOf(
    Recipe(
        id = "4704",
        servings = "4",
        prepTimeMinutes = "5",
        name = "Low-Carb Avocado Chicken",
        thumbnailUrl = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/45b4efeb5d2c4d29970344ae165615ab/FixedFBFinal.jpg",
        description = "This chicken salad is a lunchtime delight! Packed with creamy avocado, tender chicken, and crunchy veggies, it's a healthy and satisfying meal that won't weigh you down. Tossed in a tangy yogurt dressing with a hint of spice, it's a flavor explosion that's perfect for a light meal."
    ),
    Recipe(
        id = "5466",
        servings = "4",
        prepTimeMinutes = "5",
        name = "Tomato And Anchovy Pasta",
        thumbnailUrl = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/216182.jpg",
        description = "Savor the bold flavors of this Tomato and Anchovy Pasta, a perfect weeknight meal that's both simple and satisfying. With a zesty tomato sauce and umami-packed anchovies, this dish will have your taste buds dancing in no time!"
    ),
    Recipe(
        id = "5814",
        servings = "4",
        prepTimeMinutes = "5",
        name = "Blueberry Cream Muffins",
        thumbnailUrl = "https://img.buzzfeed.com/tasty-app-user-assets-prod-us-east-1/recipes/4e9524578f544c888af761e10630593b.jpeg",
        description = "Yummy blueberry muffins."
    )
)

fun openRecipeDetail(context: Context, listItem: Recipe) {
    val intent = Intent(context, RecipeDetailsActivity::class.java)
    intent.putExtra("RESULTS", listItem)
    context.startActivity(intent)
}

@Composable
fun RowItem(url: String, name: String, prepTime: String, servingSize: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(16.dp)),
    )
    {
        Row {
            AsyncImage(
                model = url,
                contentDescription = "Thumbnail Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    text = name,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "Serving Size: $servingSize",
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "Prep Time: $prepTime minutes",
                    modifier = Modifier.padding(2.dp)
                )
//            Text(
//                text = "Savor the bold flavors of this Tomato and Anchovy Pasta, a perfect weeknight meal that's both simple and satisfying. With a zesty tomato sauce and umami-packed anchovies, this dish will have your taste buds dancing in no time!"
//
//            )
            }
        }
    }
}

@Composable
fun RecipeList(recipeList: List<Recipe>, context: Context) {
    LazyColumn {
        items(recipeList.size) { index ->
            val listItem = recipeList[index]
            RowItem(
                url = checkNotNull(listItem.thumbnailUrl),
                name = checkNotNull(listItem.name),
                prepTime = listItem.prepTimeMinutes ?: "0",
                servingSize = listItem.servings ?: "0",
                onClick = {
                    openRecipeDetail(context, listItem)
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NomscapeTheme {
        RecipeList(recipeList = resultList, context = LocalContext.current)
//        RowItem(url = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/216182.jpg")

    }
}