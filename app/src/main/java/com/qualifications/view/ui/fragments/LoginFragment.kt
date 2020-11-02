package com.qualifications.view.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.qualifications.R
import com.qualifications.model.LoginRequest
import com.qualifications.model.User
import com.qualifications.network.ApiCallback
import com.qualifications.network.SessionManager
import com.qualifications.view.ui.activities.MainActivity
import com.qualifications.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var userOrEmailInput: TextInputLayout
    private lateinit var passwordInput: TextInputLayout
    private lateinit var userViewModel: UserViewModel
    private lateinit var sessionManager: SessionManager


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        userOrEmailInput = view.findViewById(R.id.et_user_or_email)
        passwordInput = view.findViewById(R.id.et_password)
        userViewModel = UserViewModel(view.context)

        sessionManager = SessionManager(view.context)

        sessionManager.fetchAuthToken()?.let {
            if (it.isNotBlank()) {
                val intent = Intent(view.context , MainActivity::class.java)
                startActivity(intent)
            } else {
                println("No user token")
            }
        }

        val btLogin = view.findViewById<Button>(R.id.bt_login)
        btLogin.setOnClickListener {
            val userOrPassword = userOrEmailInput.editText?.text.toString()
            val password = passwordInput.editText?.text.toString()

            if (userOrPassword.isNotEmpty() && password.isNotEmpty()) {
                val loginRequest = LoginRequest(userOrPassword , password)
                userViewModel.login(loginRequest , object : ApiCallback<User> {
                    override fun onResponse(result: Response<User>) {
                        if (result.isSuccessful) {
                            val body = result.body()
                            if (body != null) {
                                sessionManager.saveAuthToken(body.token , body.id)

                                val intent = Intent(view.context , MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                tv_sign_in_error.text = result.errorBody()?.string()
                                tv_sign_in_error.visibility = View.VISIBLE
                            }
                        } else {
                            tv_sign_in_error.text = result.errorBody()?.string()
                            tv_sign_in_error.visibility = View.VISIBLE
                        }
                    }

                    override fun onFailure(exception: Throwable) {
                        println("Error: " + exception.message)
                    }

                })
            }
        }

        tv_not_register.setOnClickListener {
            findNavController().navigate(R.id.registerUserFragment)
        }
    }
}