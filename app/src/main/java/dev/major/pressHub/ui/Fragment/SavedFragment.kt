package dev.major.pressHub.ui.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dev.major.pressHub.R
import dev.major.pressHub.ui.Activity.MainActivity
import dev.major.pressHub.ui.NewsViewModal

class SavedFragment : Fragment(R.layout.fragment_saved) {
    lateinit var viewModal: NewsViewModal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModal = (activity as MainActivity).viewModal
    }
}