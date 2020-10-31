package com.qualifications.view.ui.fragments

import android.content.Intent
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
import com.qualifications.network.SessionManager
import com.qualifications.view.ui.activities.MainActivity
import com.qualifications.viewmodel.UserViewModel
import retrofit2.Response

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

        userViewModel = UserViewModel(view.context)

        userInput = view.findViewById(R.id.et_user)
        emailInput = view.findViewById(R.id.et_email)
        passwordInput = view.findViewById(R.id.et_password)

        val btRegister = view.findViewById<Button>(R.id.bt_register_user)

        btRegister.setOnClickListener {
            val username = userInput.editText?.text.toString()
            val email = emailInput.editText?.text.toString()
            val password = passwordInput.editText?.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                val user = User(username , email , password)

                userViewModel.saveUser(user , object : ApiCallback<User> {
                    override fun onResponse(result: Response<User>) {
                        if (result.isSuccessful) {
                            val body = result.body()
                            if (body != null) {
                                val sessionManager = SessionManager(view.context)
                                sessionManager.saveAuthToken(body.token , body.id)

                                val intent = Intent(view.context , MainActivity::class.java)
                                startActivity(intent)
                            }
                        }

                    }

                    override fun onFailure(exception: Throwable) {
                        println("Error: " + exception.message)
                    }

                })
            }
        }
    }
}