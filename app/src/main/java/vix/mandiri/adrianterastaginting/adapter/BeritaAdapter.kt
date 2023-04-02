package vix.mandiri.adrianterastaginting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vix.mandiri.adrianterastaginting.databinding.NewsItemBinding
import vix.mandiri.adrianterastaginting.model.Berita

class BeritaAdapter(private val listener: OnItemClickListener) : ListAdapter<Berita, BeritaAdapter.NewsViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Berita>() {
        override fun areItemsTheSame(oldItem: Berita, newItem: Berita): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Berita, newItem: Berita): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)

    }

    interface OnItemClickListener {
        fun onItemClick(article: Berita)
    }

    inner class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val article = getItem(position)
                listener.onItemClick(article)
            }
        }

        fun bind(article: Berita) {
            binding.beritaTitleTextView.text = article.title
            binding.beritaDescriptionTextView.text = article.description
            binding.beritaDateTextView.text = article.publishedAt
            Glide
                .with(binding.beritaImageView.context)
                .load(article.urlToImage)
                .centerCrop()
                .into(binding.beritaImageView)

        }
    }

}