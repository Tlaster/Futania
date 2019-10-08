package moe.tlaster.futania.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_search.*
import moe.tlaster.futania.R
import moe.tlaster.futania.databinding.FragmentSearchBinding
import moe.tlaster.futania.viewmodel.SearchViewModel

class SearchFragment : BindingFragment<FragmentSearchBinding>() {
    override val layout: Int
        get() = R.layout.fragment_search


    override fun initBinding(binding: FragmentSearchBinding) {
        binding.viewmodel = activity?.run {
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.menu.findItem(R.id.button_search)?.let {
//            it.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
//                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
//                    return true
//                }
//
//                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
//                    return true
//                }
//
//            })
            it.actionView as? SearchView
        }?.let {
            it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }
}