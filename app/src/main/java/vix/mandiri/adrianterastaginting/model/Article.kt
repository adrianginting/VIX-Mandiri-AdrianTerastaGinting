package vix.mandiri.adrianterastaginting.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String
) : Parcelable {

}