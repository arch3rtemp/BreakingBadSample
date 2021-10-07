package com.example.breakingbadsample.domain.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class CharacterModel(
    @PrimaryKey
    @SerializedName("char_id")
    val charId: Int,
    @SerializedName("appearance")
    val appearance: List<Int>,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("occupation")
    val occupation: List<String>,
    @SerializedName("portrayed")
    val portrayed: String,
    @SerializedName("status")
    val status: String
)