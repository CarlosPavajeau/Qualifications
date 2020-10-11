package com.qualifications.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qualifications.R
import com.qualifications.model.Subject

class SubjectAdapter(private val subjectListener: SubjectListener) : RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val code: TextView = itemView.findViewById(R.id.subject_code)
        val name: TextView = itemView.findViewById(R.id.subject_name)
        val qualifications: TextView = itemView.findViewById(R.id.subject_qualifications)
        val definitive: TextView = itemView.findViewById(R.id.subject_definitive)
    }

    var subjects = ArrayList<Subject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.subject_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subject = subjects[position]
        val context = holder.code.context
        holder.code.text = context.getString(R.string.subject_item_code, subject.code)
        holder.name.text = subject.name
        holder.qualifications.text = context.getString(
            R.string.subject_qualifications_item,
            subject.qualifications[0].total,
            subject.qualifications[1].total,
            subject.qualifications[2].total
        )

        holder.definitive.text = context.getString(R.string.subject_definitive_item, subject.definitive)

        holder.itemView.setOnClickListener {
            subjectListener.onSubjectTap(subject, position)
        }
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    fun updateData(newSubjects: List<Subject>) {
        subjects.clear()
        subjects.addAll(newSubjects)
        notifyDataSetChanged()
    }
}