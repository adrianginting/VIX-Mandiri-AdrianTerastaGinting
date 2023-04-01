package vix.mandiri.adrianterastaginting.ui.semua_berita

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vix.mandiri.adrianterastaginting.adapter.NewsAdapter
import vix.mandiri.adrianterastaginting.databinding.FragmentSemuaBeritaBinding
import vix.mandiri.adrianterastaginting.model.Article
import vix.mandiri.adrianterastaginting.model.NewsApiResponse
import vix.mandiri.adrianterastaginting.model.SemuaBerita

class SemuaBeritaFragment : Fragment(), NewsAdapter.OnItemClickListener {

    private var _binding: FragmentSemuaBeritaBinding? = null
    private lateinit var adapter: NewsAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSemuaBeritaBinding.inflate(inflater, container, false)

        adapter = NewsAdapter(this)
        binding.newsRecyclerView.adapter = adapter
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(context)

        var data = binding.searchBerita.query
        binding.btnSearchBerita.setOnClickListener {
            Toast.makeText(context,"Mencari $data pada berita",Toast.LENGTH_SHORT).show()
            getBerita("$data")
        }

        return binding.root
    }

    fun getBerita(cari: String){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SemuaBerita::class.java)
        service.getEverything(cari,  "107abca9ae3541e1a85b3ef5b31f1be9")
            .enqueue(object : Callback<NewsApiResponse> {
                override fun onResponse(
                    call: Call<NewsApiResponse>,
                    response: Response<NewsApiResponse>
                ) {
                    if (response.isSuccessful) {
                        val articles = response.body()?.articles ?: emptyList()
                        adapter.submitList(articles)
                    }
                }

                override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                    // handle failure
                }
            })
    }

    override fun onItemClick(article: Article) {
        Toast.makeText(context,"Ke $article ", Toast.LENGTH_SHORT).show()
//        val intent = Intent(activity, ArticleDetailActivity::class.java)
//        intent.putExtra("article", article)
//        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}