package com.qualifications.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.qualifications.R

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterUserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_user , container , false)
    }
}