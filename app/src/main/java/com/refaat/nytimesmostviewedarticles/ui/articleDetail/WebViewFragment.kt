package com.refaat.nytimesmostviewedarticles.ui.articleDetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import android.webkit.WebResourceRequest
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.navArgs
import com.refaat.nytimesmostviewedarticles.R
import com.refaat.nytimesmostviewedarticles.databinding.ArticleDetailFragmentBinding
import com.refaat.nytimesmostviewedarticles.databinding.FragmentWebViewBinding
import com.refaat.nytimesmostviewedarticles.ui.MainActivity

class WebViewFragment : Fragment() {

    val args: WebViewFragmentArgs by navArgs()

    private var _binding: FragmentWebViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        val view = binding.root

        val myWebView: WebView = binding.webview
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (Uri.parse(url).host == "www.nytimes.com") {
                    // This is my web site, so do not override; let my WebView load the page
                    return false
                }
                // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
                Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    startActivity(this,null)
                }
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.imgLoading.visibility = View.INVISIBLE
            }
        }




        myWebView.settings.javaScriptEnabled = true
        args.theSelectedArticle.url?.let { myWebView.loadUrl(it) }


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


//    private class MyWebViewClient(val context: Context) : WebViewClient() {
//
//
//
//
//        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//            if (Uri.parse(url).host == "www.nytimes.com") {
//                // This is my web site, so do not override; let my WebView load the page
//                return false
//            }
//            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
//            Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
//                startActivity(context,this,null)
//            }
//            return true
//        }
//    }

}


