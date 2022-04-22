package com.nadikarim.submission2.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.nadikarim.submission2.data.model.UserSession
import com.nadikarim.submission2.databinding.ActivityLoginBinding
import com.nadikarim.submission2.ui.main.MainActivity
import com.nadikarim.submission2.ui.register.RegisterActivity
import com.nadikarim.submission2.utils.DataStoreViewModel
import com.nadikarim.submission2.vo.Result
import com.uk.tastytoasty.TastyToasty
import dagger.hilt.android.AndroidEntryPoint

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModelViewModel by viewModels<LoginViewModel>()
    private val dataStoreViewModel by viewModels<DataStoreViewModel>()
    //private lateinit var pref: SharedPreferences
    //lateinit var userPref: LoginPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Login"

        //setupPreference()
        setupViewModel()
        action()
        playAnimation()

    }

    /*
    private fun setupPreference() {
        pref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        userPref = LoginPreference(this)
    }

     */

    private fun setupViewModel() {
        viewModelViewModel.isLoading.observe(this) { showLoading(it) }
        viewModelViewModel.toastMessage.observe(this) { toast(it) }
    }

    private fun action() {
        binding.btnLogin.setOnClickListener {
            login()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity as Activity).toBundle()
            )
        }
    }

    private fun login() {
        val email = binding.tvEmail.text.toString().trim()
        val password = binding.tvPassword.text.toString().trim()
        when {
            email.isEmpty() -> {
                binding.tilEmail.error = "Masukkan email"
            }
            password.isEmpty() -> {
                binding.textInputLayout.error = "Masukkan password"
            }
            else -> {
                /*
                viewModelViewModel.login2(email, password).observe(this) {
                    if (it != null) {
                        when(it) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                AlertDialog.Builder(this).apply {
                                    setTitle("berhasil login")
                                    setMessage("Selamat Datang, ${it.data.loginResult.name}!")
                                    setPositiveButton("Lanjut") { _, _ ->
                                        val intent = Intent(context, MainActivity::class.java)
                                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                        startActivity(intent,
                                            ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity as Activity).toBundle()
                                        )
                                        finish()
                                    }
                                    create()
                                    show()
                                }
                                val login = it.data.loginResult
                                savedSession(UserSession(login.name, login.token, login.userId, true))
                            }
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                TastyToasty.error(this@LoginActivity, "Error").show()
                            }
                        }
                    }
                }

                 */
                viewModelViewModel.loginUser(email, password)

                viewModelViewModel.userLogin.observe(this) {
                    binding.progressBar.visibility = View.VISIBLE
                    if (it != null) {
                        AlertDialog.Builder(this).apply {
                            setTitle("berhasil login")
                            setMessage("Selamat Datang, ${it.name}!")
                            setPositiveButton("Lanjut") { _, _ ->
                                val intent = Intent(context, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent,
                                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity as Activity).toBundle()
                                )
                                finish()
                            }
                            create()
                            show()
                        }
                        savedSession(UserSession(it.name, it.token, it.userId, true))
                        //saveSession(UserSession(it.name, it.token, it.userId, true))
                    }

                }


            }
        }
    }

    /*
    private fun saveSession(user: UserSession) {
        userPref.setUser(user)
    }
     */

    private fun savedSession(user: UserSession) {
        dataStoreViewModel.setSession(user)
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val email = ObjectAnimator.ofFloat(binding.tvRegister, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(email)
            startDelay = 500
        }.start()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}