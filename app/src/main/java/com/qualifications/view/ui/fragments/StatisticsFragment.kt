package com.qualifications.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.qualifications.R
import com.qualifications.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_statistics.*

/**
 * A simple [Fragment] subclass.
 * Use the [StatisticsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatisticsFragment : Fragment() {
    private lateinit var subjectViewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        subjectViewModel = SubjectViewModel(view.context)
        subjectViewModel.refresh()

        subjectViewModel.subjects.observe(viewLifecycleOwner, { it ->
            if (it.isNotEmpty()) {
                val subjectsDefinitive = ((it.map { it.definitive }).reduce { acc, fl -> acc + fl } / it.size)
                definitive.text = "Definitive: $subjectsDefinitive"
                registered_subjects.text = "Registered subjects: ${it.size}"

                approved_subjects.text = "Approved subjects: ${it.filter { it.definitive >= 3.0 }.size}"
                failed_subjects.text = "Failed subjects: ${it.filter { it.definitive < 3.0 }.size}"
            }
        })
    }
}