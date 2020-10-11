package com.qualifications.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.qualifications.R
import com.qualifications.model.Subject
import com.qualifications.view.adapter.SubjectAdapter
import com.qualifications.view.adapter.SubjectListener
import com.qualifications.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_subjects.*

/**
 * A simple [Fragment] subclass.
 * Use the [SubjectsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubjectsFragment : Fragment(), SubjectListener {
    private lateinit var subjectAdapter: SubjectAdapter
    private lateinit var subjectViewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subjects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subjectViewModel = SubjectViewModel(view.context)
        subjectViewModel.refresh()

        subjectAdapter = SubjectAdapter(this)

        subjects.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = subjectAdapter
        }

        subjectViewModel.subjects.observe(viewLifecycleOwner, Observer {
            subjects -> subjectAdapter.updateData(subjects)
        })

        subjectViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                rv_loading.visibility = View.INVISIBLE
            }
        })
    }

    override fun onSubjectTap(subject: Subject, index: Int) {
        val bundle = bundleOf("subject" to subject)
        findNavController().navigate(R.id.registerQualificationsFragment, bundle)
    }
}