package edu.northeastern.nomscape.viewmodels.uistate

import edu.northeastern.nomscape.models.Recipe

sealed class UiState {
    data class Success(val recipeList: List<Recipe>) : UiState()
    data class Error(val message: String) : UiState()
    object Loading : UiState()
}