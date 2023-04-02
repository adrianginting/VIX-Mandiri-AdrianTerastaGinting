package vix.mandiri.adrianterastaginting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import vix.mandiri.adrianterastaginting.databinding.ActivityDetailBeritaBinding
import vix.mandiri.adrianterastaginting.model.Berita
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailBeritaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBeritaBinding
    private lateinit var berita: Berita


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getParcelableExtra<Berita>("article")


        val formatTanggal = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = LocalDate.parse(article?.publishedAt, formatTanggal)
        val hari = date.dayOfWeek.toString()
        val tanggal = date.dayOfMonth.toString()
        val bulan = date.month.toString()
        val tahun = date.year.toString()

        binding.beritaTitleTextView.text = article?.title
        binding.beritaPublishedAtTextView.text = "Published At :\n$hari, $tanggal $bulan $tahun"
        binding.beritaAuthorTextView.text = "Author :\n"+article?.author
        binding.beritaContentTextView.text = "Content :\n"+article?.content
        binding.beritaUrlTextView.text = "Link :\n"+article?.url

        Glide
            .with(this)
            .load(article?.urlToImage)
            .centerCrop()
            .into(binding.beritaImageView)

    }

}