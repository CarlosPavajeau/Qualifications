package com.qualifications.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.qualifications.R
import com.qualifications.model.Qualification
import com.qualifications.model.Subject
import com.qualifications.view.adapter.ActivityAdapter
import kotlinx.android.synthetic.main.fragment_register_qualifications.*

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterQualificationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterQualificationsFragment : Fragment() {
    private lateinit var subject: Subject
    private var currentCort: Int = 0

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

        activityAdapter = ActivityAdapter()

        subject = arguments?.getSerializable("subject") as Subject
        register_activities.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = activityAdapter
        }

        subject_name.text = "Subject name: ${subject.name}"

        cort_radios.setOnCheckedChangeListener { group, checkedId ->

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
                activityAdapter.updateData(subject.qualifications[currentCort - 1].activities)
            }
        }

        add_activity_button.setOnClickListener {
            if (currentCort != 0) {
                val bundle = bundleOf("qualification" to subject.qualifications[currentCort - 1])
                findNavController().navigate(R.id.registerActivityFragment, bundle)
            }
        }
    }
}