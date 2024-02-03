package edu.northeastern.nomscape.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    @SerializedName("thumbnail_url") var thumbnailUrl : String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("description") var description  : String? = null,
    @SerializedName("num_servings") var servings: String? = null,
    @SerializedName("prep_time_minutes") var prepTimeMinutes  : String? = null,
    @SerializedName("original_video_url") var videoUrl  : String? = null,
    @SerializedName("instructions") var instructions: List<Instructions>? = listOf()
) : Parcelable