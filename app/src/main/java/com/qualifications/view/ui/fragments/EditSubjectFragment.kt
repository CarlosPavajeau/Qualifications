package com.qualifications.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.qualifications.R
import com.qualifications.model.Subject
import com.qualifications.network.ApiCallback
import com.qualifications.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_edit_subject.*

/**
 * A simple [Fragment] subclass.
 * Use the subject_name_field.editText?.text[EditSubjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditSubjectFragment : Fragment() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var subject: Subject

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_subject , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        subject = arguments?.getSerializable("subject") as Subject
        subjectViewModel = SubjectViewModel()

        subject_code.text = view.context.getString(R.string.subject_item_code, subject.code)
        subject_name_field.editText?.setText(subject.name)

        update_subject_button.setOnClickListener {
            updateSubject()
        }
    }

    private fun updateSubject() {
        if (subject_name_field.editText?.text?.isNotBlank()!!) {
            subject = Subject(subject.code, subject_name_field.editText?.text.toString())

            subjectViewModel.updateSubject(subject, object : ApiCallback<Subject> {
                override fun onFail(exception: Throwable) {
                    return
                }

                override fun onSuccess(result: Subject?) {
                    findNavController().navigate(R.id.subjectsFragment)
                }
            })
        }
    }
}