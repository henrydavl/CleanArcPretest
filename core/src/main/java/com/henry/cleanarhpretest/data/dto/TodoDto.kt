package com.henry.cleanarhpretest.data.dto

import com.squareup.moshi.Json

data class TodoDto(
    @field:Json(name = "userId") val userId: Int?,
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "completed") val completed: Boolean?,
)
