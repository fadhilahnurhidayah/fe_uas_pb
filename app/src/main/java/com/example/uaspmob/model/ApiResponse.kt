package com.example.uaspmob.model

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T?
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> =
            ApiResponse(true, "Success", data)

        fun <T> error(message: String, data: T? = null): ApiResponse<T> =
            ApiResponse(false, message, data)

        fun <T> loading(): ApiResponse<T> =
            ApiResponse(false, "Loading", null)
    }
}