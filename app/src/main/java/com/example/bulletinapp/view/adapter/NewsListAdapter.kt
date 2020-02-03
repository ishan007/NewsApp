package com.example.bulletinapp.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bulletinapp.R
import com.example.bulletinapp.databinding.NewsListItemBinding
import com.example.bulletinapp.domain.entities.News

class NewsListAdapter(private val newsList: List<News>,
                      private val newsSelectionListener: NewsSelectionListener)
    : RecyclerView.Adapter<NewsListAdapter.NewsItemVH>(){

    class NewsItemVH(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemVH {
        val viewDataBinding = DataBindingUtil.inflate<NewsListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.news_list_item,
            parent, false)

        viewDataBinding.root.setOnClickListener {
            newsSelectionListener.onNewsSelected(viewDataBinding.news!! )
        }

        return NewsItemVH(viewDataBinding)
    }

    override fun onBindViewHolder(holder: NewsItemVH, position: Int) {
        holder.binding.news = newsList[position]
    }


    class NewsSelectionListener(val onNewsSelection: (news: News)-> Unit){
        fun onNewsSelected(news: News) = onNewsSelection(news)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url:String?) {
    Glide.with(view.context)
        .setDefaultRequestOptions(RequestOptions
            .placeholderOf(R.drawable.default_image_thumbnail)
            .error(R.drawable.default_image_thumbnail))
        .load(Uri.parse(url ?: ""))
        .into(view)
}
