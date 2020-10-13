package com.qualifications.view.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.qualifications.R
import com.qualifications.model.Activity
import com.qualifications.model.Qualification
import com.qualifications.model.Subject
import com.qualifications.view.adapter.ActivityAdapter
import com.qualifications.view.adapter.ActivityListener
import com.qualifications.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_register_qualifications.*

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterQualificationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterQualificationsFragment : Fragment(), ActivityListener {
    private lateinit var subject: Subject
    private var currentCort: Int = 0

    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var activityAdapter: ActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_qualifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subjectViewModel = SubjectViewModel(view.context)
        activityAdapter = ActivityAdapter(this)

        subject = arguments?.getSerializable("subject") as Subject
        register_activities.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = activityAdapter
        }

        subject_name.text = view.context.getString(R.string.register_qualification_subject_name_text, subject.name)

        cort_radios.setOnCheckedChangeListener { _ , checkedId ->

            currentCort = when (checkedId) {
                R.id.cort_1 -> {
                    1
                }
                R.id.cort_2 -> {
                    2
                }
                R.id.cort_3 -> {
                    3
                }
                else -> 0
            }

            if (currentCort != 0) {
                val qualification = subject.qualifications[currentCort - 1]
                activityAdapter.updateData(qualification.activities)
                val totalActivitiesPercent: Float = qualification.totalActivitiesPercent * 100
                val totalPartial: Float = qualification.totalPartial

                percent_complete.text = view.context.getString(R.string.percent_complete_with_cort, totalActivitiesPercent)
                cort_definitive.text = view.context.getString(R.string.cort_definitive_with_cort, totalPartial)
            }
        }

        add_activity_button.setOnClickListener {
            if (currentCort != 0) {
                val bundle = bundleOf("qualification" to subject.qualifications[currentCort - 1])
                findNavController().navigate(R.id.registerActivityFragment, bundle)
            }
        }
    }

    override fun onActivityEditButtonTap(activity: Activity , position: Int) {
        val bundle = bundleOf("activity" to activity)
        findNavController().navigate(R.id.editActivityFragment, bundle)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityDeleteButtonTap(activity: Activity , position: Int) {
        if (subjectViewModel.deleteActivity(activity.id)) {
            val qualification = subject.qualifications[currentCort - 1]
            qualification.activities.removeIf { it.id == activity.id }
            activityAdapter.updateData(qualification.activities)
            percent_complete.text = context?.getString(R.string.percent_complete_with_cort, qualification.totalActivitiesPercent * 100)
        }
    }
}