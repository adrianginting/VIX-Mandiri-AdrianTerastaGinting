package vix.mandiri.adrianterastaginting.model

import retrofit2.http.GET
import retrofit2.http.Query

interface SemuaBerita {
    @GET("everything")
    fun getEverything(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): retrofit2.Call<BeritaApiResponse>
}