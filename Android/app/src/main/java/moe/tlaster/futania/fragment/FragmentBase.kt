package moe.tlaster.futania.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class FragmentBase<T : ViewDataBinding> : Fragment() {

    abstract val layout: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<T>(inflater, layout, container, false)
        binding.lifecycleOwner = this
        initBinding(binding)
        return binding.root
    }

    abstract fun initBinding(binding: T)
}