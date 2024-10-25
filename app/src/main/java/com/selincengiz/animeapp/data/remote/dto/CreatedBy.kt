package com.selincengiz.animeapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CreatedBy(
    @SerializedName("credit_id")
    val creditId: String?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("profile_path")
    val profilePath: String?,
)
