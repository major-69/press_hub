package dev.major.pressHub.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import dev.major.pressHub.R
import dev.major.pressHub.ui.Activity.MainActivity
import dev.major.pressHub.ui.NewsViewModal

class ArticleFragment : Fragment(R.layout.fragment_artical) {
    private lateinit var viewModal: NewsViewModal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModal = (activity as MainActivity).viewModal
    }

}