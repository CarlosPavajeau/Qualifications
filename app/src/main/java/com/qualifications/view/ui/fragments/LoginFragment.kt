package com.qualifications.view.ui.fragments

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
import com.qualifications.network.ServiceBuilder
import com.qualifications.network.SessionManager
import com.qualifications.viewmodel.UserViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var userOrPasswordInput: TextInputLayout
    private lateinit var passwordInput: TextInputLayout
    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        userOrPasswordInput = view.findViewById(R.id.et_user_or_email)
        passwordInput = view.findViewById(R.id.et_password)
        userViewModel = UserViewModel()

        val sessionManager = ServiceBuilder.context?.let { SessionManager(it) }

        sessionManager?.fetchAuthToken()?.let {
            if (it.isNotBlank()) {
                findNavController().navigate(R.id.subjectsFragment)
            } else {
                println("No user token")
            }
        }

        val btLogin = view.findViewById<Button>(R.id.bt_login)
        btLogin.setOnClickListener {
            val userOrPassword = userOrPasswordInput.editText?.text.toString()
            val password = passwordInput.editText?.text.toString()

            if (userOrPassword.isNotEmpty() && password.isNotEmpty()) {
                val loginRequest = LoginRequest(userOrPassword , password)
                userViewModel.login(loginRequest , object : ApiCallback<User> {
                    override fun onSuccess(result: User?) {
                        if (result != null) {
                            val sessionManager = ServiceBuilder.context?.let { SessionManager(it) }
                            sessionManager?.saveAuthToken(result.token , result.id)
                            findNavController().navigate(R.id.subjectsFragment)
                        } else {
                            println("User are null")
                        }

                    }

                    override fun onFail(exception: Throwable) {
                        println("Error: " + exception.message)
                    }

                })
            }
        }
    }
}