package edu.northeastern.nomscape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.northeastern.nomscape.repository.RecipesRepository
import edu.northeastern.nomscape.viewmodels.uistate.UiState
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val repository: RecipesRepository = RecipesRepository()

    fun fetchRecipeList() : LiveData<UiState> {
        val recipeLiveData = MutableLiveData<UiState>()
        try {
            viewModelScope.launch {
                val recipes = repository.fetchRecipes().results
                recipeLiveData.value = UiState.Success(recipes)
            }
        } catch (e: Exception) {
            recipeLiveData.value = UiState.Error(e.message.toString())
        }
        return recipeLiveData
    }
}