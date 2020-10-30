package com.qualifications.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.qualifications.R
import com.qualifications.model.User
import com.qualifications.network.ApiCallback
import com.qualifications.network.ServiceBuilder
import com.qualifications.network.SessionManager
import com.qualifications.viewmodel.UserViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterUserFragment : Fragment() {
    private lateinit var userInput: TextInputLayout
    private lateinit var emailInput: TextInputLayout
    private lateinit var passwordInput: TextInputLayout

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_user , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        userInput = view.findViewById(R.id.et_user)
        emailInput = view.findViewById(R.id.et_email)
        passwordInput = view.findViewById(R.id.et_password)

        val sessionManager = ServiceBuilder.context?.let { SessionManager(it) }

        val btRegister = view.findViewById<Button>(R.id.bt_register_user)

        btRegister.setOnClickListener {
            val username = userInput.editText?.text.toString()
            val email = emailInput.editText?.text.toString()
            val password = passwordInput.editText?.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                val user = User(username , email , password)

                userViewModel.saveUser(user , object : ApiCallback<User> {
                    override fun onSuccess(result: User?) {
                        if (result != null) {
                            
                        }
                    }

                    override fun onFail(exception: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }
        }
    }
}