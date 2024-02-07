package dev.major.pressHub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.major.pressHub.databinding.NewsLayoutBinding
import dev.major.pressHub.helper.Helper
import dev.major.pressHub.modal.Article
import javax.xml.transform.ErrorListener

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    lateinit var binding: NewsLayoutBinding
    inner class ArticleViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

    }
    private val differCallback = object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ  = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        binding = NewsLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
      return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(binding.newsImage)
            binding.trendingContent.text = article.content
            binding.headlineTitle.text= article.title
            binding.discriptionText.text = article.description
            binding.publishedDate.text = Helper.convertUTCtoLocalDateTime(article.publishedAt)

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }

            if (position == 0){
                binding.viewUpper.visibility = View.INVISIBLE
            }

        }

    }
    private var onItemClickListener: ((Article)-> Unit)? = null

    private fun setOnClickListener(listener: (Article) -> Unit){

    }




}