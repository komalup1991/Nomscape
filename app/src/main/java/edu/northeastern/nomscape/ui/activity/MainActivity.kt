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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import edu.northeastern.nomscape.models.Results
import edu.northeastern.nomscape.repository.RecipesRepository
import edu.northeastern.nomscape.ui.theme.NomscapeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            NomscapeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    var recipeList by remember {
                        mutableStateOf(listOf<Results>())
                    }

                    RecipesRepository().fetchRecipes { resultsList ->
                        recipeList = resultsList
                    }

                    LazyColumn() {
                        items(recipeList.size) { index ->
                            val listItem = recipeList[index]
                            RowItem(
                                url = checkNotNull(listItem.thumbnailUrl),
                                name = checkNotNull(listItem.name),
                                prepTime = listItem.prepTimeMinutes ?: "0",
                                servingSize = listItem.servings ?: "0",
                                onClick = {
                                    openRecipeDetail(baseContext, listItem)
                                })
                        }

                    }
                }
            }
        }
    }
}

fun openRecipeDetail(context: Context, listItem: Results) {
    val intent = Intent(context, RecipeDetailsActivity::class.java)
    intent.putExtra("RESULTS", listItem)
    context.startActivity(intent)
}

@Composable
fun RowItem(url: String, name: String, prepTime: String, servingSize: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    )
    {
        AsyncImage(
            model = url,
            contentDescription = "Thumbnail Image",
            modifier = Modifier
                .width(110.dp)
                .height(110.dp)
                .padding(4.dp)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = name

            )
            Text(
                text = "Serving Size: $servingSize"
            )
            Text(
                text = "Prep Time: $prepTime minutes"
            )
//            Text(
//                text = "Savor the bold flavors of this Tomato and Anchovy Pasta, a perfect weeknight meal that's both simple and satisfying. With a zesty tomato sauce and umami-packed anchovies, this dish will have your taste buds dancing in no time!"
//
//            )
        }

    }


}

fun renderContent() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NomscapeTheme {
//        RowItem(url = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/216182.jpg")

    }
}