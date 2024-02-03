package edu.northeastern.nomscape.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
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
import edu.northeastern.nomscape.ui.theme.NomscapeTheme
import edu.northeastern.nomscape.viewmodels.RecipeViewModel
import edu.northeastern.nomscape.viewmodels.uistate.UiState


class MainActivity : ComponentActivity() {

    private val recipeViewModel = RecipeViewModel()

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
                        val uiState =
                            recipeViewModel.fetchRecipeList().observeAsState(UiState.Loading)
                        when (uiState.value) {
                            is UiState.Success ->
                                RecipeList(
                                    recipeList = (uiState.value as UiState.Success).recipeList,
                                    context = LocalContext.current
                                )
                            is UiState.Error -> Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment  = Alignment.CenterHorizontally,
                            ) { Text(text = "Unable to load data!") }
                            is UiState.Loading -> {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment  = Alignment.CenterHorizontally,
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.width(64.dp),
                                        color = MaterialTheme.colorScheme.secondary,
                                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

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
            }
        }
    }
}

@Composable
fun RecipeList(recipeList: List<Recipe>, context: Context) {
    LazyColumn {
        items(recipeList.size) { index ->
            val listItem: Recipe = recipeList[index]
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
//        RecipeList(recipeList = resultList, context = LocalContext.current)
//        RowItem(url = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/216182.jpg")

    }
}