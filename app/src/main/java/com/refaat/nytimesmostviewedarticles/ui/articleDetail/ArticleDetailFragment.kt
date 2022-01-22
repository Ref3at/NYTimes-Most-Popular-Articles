package com.refaat.nytimesmostviewedarticles.ui.articleDetail

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.refaat.nytimesmostviewedarticles.R
import com.refaat.nytimesmostviewedarticles.databinding.ArticleDetailFragmentBinding
import com.refaat.nytimesmostviewedarticles.databinding.ArticleListFragmentBinding
import com.refaat.nytimesmostviewedarticles.network.models.ArticleItem
import com.refaat.nytimesmostviewedarticles.ui.articleList.ArticleListFragmentDirections
import com.refaat.nytimesmostviewedarticles.ui.articleList.ArticleListViewModel
import com.refaat.nytimesmostviewedarticles.ui.articleList.CustomAdapter

class ArticleDetailFragment : Fragment() {


    private var _binding: ArticleDetailFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * Lazily initialize our [ArticleListViewModel].
     */
    private val viewModel: ArticleListViewModel by lazy {
        ViewModelProvider(this)[ArticleListViewModel::class.java]
    }

    val args: ArticleDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArticleDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.btnReadTheFullArticle.setOnClickListener(View.OnClickListener {
            val action = ArticleDetailFragmentDirections.actionArticleDetailFragmentToWebViewFragment(args.theSelectedArticle)
            it.findNavController().navigate(action)
        })

        bindView(args.theSelectedArticle)
        return view

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_share) {
            args.theSelectedArticle.url?.let { shareTheUrl(it) }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun shareTheUrl(s: String) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, s);
        startActivity(Intent.createChooser(shareIntent,getString(R.string.share_to)))

    }


    fun bindView(articleItem: ArticleItem){

        binding.txtTitle.setText(articleItem.title)
        binding.txtPublished.setText(articleItem.byline)
        binding.txtDate.setText(articleItem.publishedDate)


        binding.txtArticleBody.setText(articleItem.abstract)


        if(!articleItem.media.isNullOrEmpty()){
            articleItem.media!!.get(0)?.let {
                if(!it.mediaMetadata.isNullOrEmpty()){
                    it.mediaMetadata?.get(1)?.url?.let {
                        Glide.with(this)
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