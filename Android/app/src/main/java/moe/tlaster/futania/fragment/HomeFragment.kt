package moe.tlaster.futania.fragment

import androidx.lifecycle.ViewModelProviders
import moe.tlaster.futania.R
import moe.tlaster.futania.databinding.FragmentHomeBinding
import moe.tlaster.futania.viewmodel.HomeViewModel

class HomeFragment : FragmentBase<FragmentHomeBinding>() {
    override val layout: Int
        get() = R.layout.fragment_home

    override fun initBinding(binding: FragmentHomeBinding) {
        binding.viewmodel = activity?.run {
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

}