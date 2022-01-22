package com.refaat.nytimesmostviewedarticles.ui.articleList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.refaat.nytimesmostviewedarticles.R
import com.refaat.nytimesmostviewedarticles.databinding.ArticleViewItemBinding
import com.refaat.nytimesmostviewedarticles.network.models.ArticleItem

class CustomAdapter() : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private val articleList = mutableListOf<ArticleItem>()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ArticleViewItemBinding.bind(view)




        fun bindView(articleItem:ArticleItem){

            binding.txtTitle.setText(articleItem.title)
            binding.txtPublished.setText(articleItem.byline)
            binding.txtDate.setText(articleItem.publishedDate)


            if(!articleItem.media.isNullOrEmpty()){
                articleItem.media!!.get(0)?.let {
                    if(!it.mediaMetadata.isNullOrEmpty()){
                        it.mediaMetadata?.get(1)?.url?.let {
                            Glide.with(itemView.context)
                                .load(it)
                                .apply(
                                    RequestOptions()
                                        .placeholder(R.drawable.loading_animation)
                                        .error(R.drawable.ic_broken_image)
                                )
                                .into(binding.articleImage)

                        }
                    }
                }
            }
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.article_view_item, viewGroup, false)



        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bindView(articleList[position])
        viewHolder.itemView.setOnClickListener {
            val action = ArticleListFragmentDirections.actionArticleListFragmentToArticleDetailFragment(articleList[position])
            it.findNavController().navigate(action)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = articleList.size


    fun updateTheList(list: List<ArticleItem>) {
        articleList.clear()
        articleList.addAll(list)
        notifyDataSetChanged()

    }

}
