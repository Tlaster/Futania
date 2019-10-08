package moe.tlaster.futania.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_me.*
import moe.tlaster.futania.R
import moe.tlaster.futania.activity.LoginActivity
import moe.tlaster.futania.common.openActivityForResult
import moe.tlaster.futania.databinding.FragmentMeBinding
import moe.tlaster.futania.viewmodel.MeViewModel

class MeFragment : BindingFragment<FragmentMeBinding>() {
    private val LOGIN_REQUEST_CODE = 4321

    override fun initBinding(binding: FragmentMeBinding) {
        binding.viewmodel = activity?.run {
            ViewModelProviders.of(this).get(MeViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override val layout: Int
        get() = R.layout.fragment_me

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setOnClickListener {
            openActivityForResult<LoginActivity>(LOGIN_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //TODO: refresh
        }
    }

}