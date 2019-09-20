package moe.tlaster.futania.fragment

import androidx.lifecycle.ViewModelProviders
import moe.tlaster.futania.R
import moe.tlaster.futania.databinding.FragmentNotificationsBinding
import moe.tlaster.futania.viewmodel.NotificationsViewModel

class NotificationsFragment : FragmentBase<FragmentNotificationsBinding>() {
    override fun initBinding(binding: FragmentNotificationsBinding) {
        binding.viewmodel = activity?.run {
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override val layout: Int
        get() = R.layout.fragment_notifications

}