package com.sofiyashahi.noteit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(
    val title: String,
    val description: String,
    val timestamp: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
