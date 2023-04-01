package vix.mandiri.adrianterastaginting.model

import retrofit2.http.GET
import retrofit2.http.Query

interface BeritaTerkini {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): retrofit2.Call<NewsApiResponse>
}