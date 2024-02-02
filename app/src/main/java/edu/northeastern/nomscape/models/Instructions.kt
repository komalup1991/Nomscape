package edu.northeastern.nomscape.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instructions (
    @SerializedName("display_text") var displayText  : String? = null,
    @SerializedName("position") var position  : Int? = null,
): Parcelable