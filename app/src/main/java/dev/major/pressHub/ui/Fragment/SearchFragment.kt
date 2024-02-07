package dev.major.pressHub.ui.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.major.pressHub.R
import dev.major.pressHub.adapter.NewsAdapter
import dev.major.pressHub.databinding.FragmentSearchBinding
import dev.major.pressHub.ui.Activity.MainActivity
import dev.major.pressHub.ui.NewsViewModal
import dev.major.pressHub.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search) {


    private lateinit var viewModal: NewsViewModal
    private lateinit var binding: FragmentSearchBinding;
    private  lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModal = (activity as MainActivity).viewModal
        var job: Job? = null
        binding.searchView.addTextChangedListener{editable->
            job?.cancel()
            job = MainScope().launch {
                delay(500)
                editable?.let {
                    if(editable.isNotEmpty()){
                        viewModal.getSearchResult(editable.toString())
                    }
                }
            }
        }

        viewModal.searchNews.observe(viewLifecycleOwner, Observer {response->

            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    handleSearchRecyclerView()
                    response.data?.let {newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                        Log.d("checkingNews","news response: ${newsAdapter.differ.currentList.size}")
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Log.d("checkingNews ", "Error ${response.message}")
                }
                is Resource.Loading -> {
                    showProgressBar()
                    Log.d("checkingNews ", "Loading ")
                }
            }

        })


    }

    private fun handleSearchRecyclerView() {
        newsAdapter = NewsAdapter();
        binding.searchRecyclerView.apply {
            adapter = newsAdapter
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }



}