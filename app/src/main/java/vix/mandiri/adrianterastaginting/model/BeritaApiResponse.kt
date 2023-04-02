package vix.mandiri.adrianterastaginting.model

import com.google.gson.annotations.SerializedName

data class BeritaApiResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Berita>
)