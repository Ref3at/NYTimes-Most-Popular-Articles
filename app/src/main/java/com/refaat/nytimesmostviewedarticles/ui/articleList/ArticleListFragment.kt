package com.refaat.nytimesmostviewedarticles.ui.articleList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.refaat.nytimesmostviewedarticles.R
import com.refaat.nytimesmostviewedarticles.databinding.ArticleListFragmentBinding
import com.refaat.nytimesmostviewedarticles.network.PeriodApiFilter
import com.refaat.nytimesmostviewedarticles.util.SpacingItemDecoration

class ArticleListFragment : Fragment() {


    /**
     * Lazily initialize our [ArticleListViewModel].
     */
    private val viewModel: ArticleListViewModel by lazy {
        ViewModelProvider(this)[ArticleListViewModel::class.java]
    }

    private var _binding: ArticleListFragmentBinding? = null
    private val adapter = CustomAdapter();

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArticleListFragmentBinding.inflate(inflater, container, false)
        val view = binding.root



        val recyclerView: RecyclerView = binding.recyclerviewArticles
        recyclerView.addItemDecoration(SpacingItemDecoration(1, 75, false))

        recyclerView.adapter = adapter

        viewModel.articles.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.updateTheList(it)
            }else{
                adapter.updateTheList(ArrayList())
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            bindStatus(it)
        })



        val toggleGroup: MaterialButtonToggleGroup = binding.toggleGroup
        toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_last_day -> {
                        // do something when selected
                        viewModel.updateFilter(PeriodApiFilter.LAST_DAY)
                    }
                    R.id.btn_last_week -> {
                        // do something when selected
                        viewModel.updateFilter(PeriodApiFilter.LAST_WEEK)
                    }
                    R.id.btn_last_month -> {
                        // do something when selected
                        viewModel.updateFilter(PeriodApiFilter.LAST_MONTH)
                    }
                }
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            val action =
                ArticleListFragmentDirections.actionArticleListFragmentToAboutFragment()
            findNavController(this).navigate(action)
        }
        return super.onOptionsItemSelected(item)

    }

    fun bindStatus( status: NYTApiStatus?) {
        when (status) {
            NYTApiStatus.LOADING -> {
                binding.statusImage.visibility = View.VISIBLE
                binding.statusImage.setImageResource(R.drawable.loading_animation)
            }
            NYTApiStatus.ERROR -> {
                binding.statusImage.visibility= View.VISIBLE
                binding.statusImage.setImageResource(R.drawable.ic_connection_error)
            }
            NYTApiStatus.DONE -> {
                binding.statusImage.visibility = View.GONE
            }
        }
    }

}