package edu.northeastern.nomscape.models

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("thumbnail_url") var thumbnailUrl : String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("description") var description  : String? = null

)