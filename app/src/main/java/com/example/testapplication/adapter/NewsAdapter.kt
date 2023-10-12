package com.example.testapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplication.R
import com.example.testapplication.models.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.EmployeeViewHolder>(){

    inner class EmployeeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById<ImageView>(R.id.ivArticleImage)
        val source: TextView = itemView.findViewById<TextView>(R.id.tvSource)
        val title: TextView = itemView.findViewById<TextView>(R.id.tvTitle)
        val description: TextView = itemView.findViewById<TextView>(R.id.tvDescription)
        val publish: TextView = itemView.findViewById<TextView>(R.id.tvPublishedAt)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)
    fun submitList(list: List<Article>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private var onItemClickListener: ((Article) -> Unit)? = null
    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(context).load(article.urlToImage).into(holder.image)
            holder.source.text = article.source.name
            holder.title.text = article.title
            holder.description.text = article.description
            holder.publish.text = article.publishedAt
            setOnClickListener {
                Log.d("test", "ami adapter ")
                onItemClickListener?.let { it(article) }
            }
        }
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
 }