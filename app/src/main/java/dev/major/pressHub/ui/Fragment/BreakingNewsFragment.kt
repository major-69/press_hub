package dev.major.pressHub.ui.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.major.pressHub.R
import dev.major.pressHub.adapter.NewsAdapter
import dev.major.pressHub.databinding.FragmentBreakingNewsBinding
import dev.major.pressHub.ui.Activity.MainActivity
import dev.major.pressHub.ui.NewsViewModal
import dev.major.pressHub.utils.Resource

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var viewModal: NewsViewModal
    lateinit var binding: FragmentBreakingNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModal = (activity as MainActivity).viewModal
        viewModal.breakingNews.observe(viewLifecycleOwner, Observer {response->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let {newsResponse ->
                        setUpTheRecyclerView()
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {message->
                        Log.e("checkBreakingNews",message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreakingNewsBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

    private fun showProgressBar() {
       binding.loadingProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.loadingProgressBar.visibility = View.INVISIBLE
    }


    private fun setUpTheRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.breakingNewsRecycler.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}