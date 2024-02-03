package edu.northeastern.nomscape.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import edu.northeastern.nomscape.models.Recipe
import edu.northeastern.nomscape.ui.components.RecipeVideoPlayer
import edu.northeastern.nomscape.ui.theme.NomscapeTheme

class RecipeDetailsActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recipe = checkNotNull(intent.getParcelableExtra("RESULTS", Recipe::class.java))
        setContent {
            NomscapeTheme {
                Scaffold(topBar = {
                    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ), title = { Text(text = "Recipes") })
                }, containerColor = MaterialTheme.colorScheme.outlineVariant) { paddingValues ->
                    Row(modifier = Modifier.padding(paddingValues)) {
                        RecipeDetails(recipe = recipe)
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeDetails(recipe: Recipe){
    Column {
        Text(text = recipe.name.toString(),
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 10.dp),)
        AsyncImage(
            model = recipe.thumbnailUrl,
            contentDescription = "Thumbnail Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Text(text = recipe.description.toString(),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
        
        RecipeVideoPlayer(url = recipe.videoUrl.toString())
    }
    
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NomscapeTheme {
//        RecipeList(recipeList = resultList, context = LocalContext.current)
////        RowItem(url = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/216182.jpg")
//
//    }
//}