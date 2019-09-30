package moe.tlaster.futania.fragment

import androidx.lifecycle.ViewModelProviders
import moe.tlaster.futania.R
import moe.tlaster.futania.databinding.FragmentMeBinding
import moe.tlaster.futania.viewmodel.MeViewModel

class MeFragment : FragmentBase<FragmentMeBinding>() {
    override fun initBinding(binding: FragmentMeBinding) {
        binding.viewmodel = activity?.run {
            ViewModelProviders.of(this).get(MeViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override val layout: Int
        get() = R.layout.fragment_me

}