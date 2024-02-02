package edu.northeastern.nomscape.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import edu.northeastern.nomscape.models.Results
import edu.northeastern.nomscape.ui.theme.NomscapeTheme

class RecipeDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NomscapeTheme {
                // A surface container using the 'background' color from the theme
                Surface( color = MaterialTheme.colorScheme.background
                ) {
                    val resultList = intent.getParcelableExtra("RESULTS", Results::class.java)

                }
            }
        }
    }
}