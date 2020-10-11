package com.qualifications.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.qualifications.R
import com.qualifications.model.Activity

class ActivityAdapter(private val activityListener: ActivityListener) : RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.activity_name)
        val note: TextView = itemView.findViewById(R.id.activity_note)
        val percent: TextView = itemView.findViewById(R.id.activity_percent)
        val deleteButton: AppCompatImageButton = itemView.findViewById(R.id.activity_delete_button)
        val editButton: AppCompatImageButton = itemView.findViewById(R.id.activity_edit_button)
    }

    var activities = ArrayList<Activity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = activities[position]
        val context = holder.name.context

        holder.name.text = activity.name
        holder.note.text = context.getString(R.string.activity_note_item, activity.note)
        holder.percent.text = context.getString(R.string.activity_percent_item, activity.percent * 100)

        holder.deleteButton.setOnClickListener {
            activityListener.onActivityDeleteButtonTap(activity, position)
        }

        holder.editButton.setOnClickListener {
            activityListener.onActivityEditButtonTap(activity, position)
        }

    }

    override fun getItemCount(): Int {
        return activities.size
    }

    fun updateData(newActivities: List<Activity>) {
        activities.clear()
        activities.addAll(newActivities)
        notifyDataSetChanged()
    }
}