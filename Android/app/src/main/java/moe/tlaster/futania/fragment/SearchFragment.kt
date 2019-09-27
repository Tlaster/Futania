package moe.tlaster.futania.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import moe.tlaster.futania.R
import moe.tlaster.futania.databinding.FragmentSearchBinding
import moe.tlaster.futania.viewmodel.SearchViewModel
import java.lang.Exception

class SearchFragment : FragmentBase<FragmentSearchBinding>() {
    override val layout: Int
        get() = R.layout.fragment_search


    override fun initBinding(binding: FragmentSearchBinding) {
        binding.viewmodel = activity?.run {
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }
}