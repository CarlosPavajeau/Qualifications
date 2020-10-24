package com.qualifications.view.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.qualifications.R
import com.qualifications.model.Activity
import com.qualifications.model.Qualification
import com.qualifications.network.ApiCallback
import com.qualifications.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_edit_activity.*

/**
 * A simple [Fragment] subclass.
 * Use the [EditActivityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditActivityFragment : DialogFragment() {
    private lateinit var activity: Activity

    private lateinit var nameField: TextInputLayout
    private lateinit var noteField: TextInputLayout
    private lateinit var percentField: TextInputLayout

    private lateinit var subjectViewModel: SubjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_activity , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        activity = arguments?.getSerializable("activity") as Activity

        subjectViewModel = SubjectViewModel()

        nameField = view.findViewById(R.id.activity_name_field)
        noteField = view.findViewById(R.id.activity_note_field)
        percentField = view.findViewById(R.id.activity_percent_field)

        nameField.editText?.setText(activity.name)
        noteField.editText?.setText(activity.note.toString())
        percentField.editText?.setText(activity.percent.toString())

        update_activity_button.setOnClickListener {
            updateActivity()
        }
    }

    private fun updateActivity() {
        val nameText = nameField.editText?.text.toString()
        val noteText = noteField.editText?.text.toString().toFloat()
        val percentText = percentField.editText?.text.toString().toFloat()

        if (nameText.isNullOrEmpty() || noteText > 5.0 || percentText > 1.0)
            return

        activity.name = nameText
        activity.note = noteText
        activity.percent = percentText

        subjectViewModel.updateActivity(activity, object : ApiCallback<Activity> {
            override fun onFail(exception: Throwable) {
                return
            }

            override fun onSuccess(result: Activity?) {
                dismiss()
                findNavController().navigate(R.id.subjectsFragment)
            }
        })
    }
}