package moe.tlaster.futania.activity

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import moe.tlaster.futania.R
import moe.tlaster.futania.databinding.ActivityLoginBinding
import moe.tlaster.futania.viewmodel.LoginViewModel

class LoginActivity : BindingActivity<ActivityLoginBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_login

    override fun initBinding(binding: ActivityLoginBinding) {
        binding.viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java).also {
            it.loginSuccess.observe(this, Observer<Boolean> {
                if (it) {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            })
        }
    }

}


