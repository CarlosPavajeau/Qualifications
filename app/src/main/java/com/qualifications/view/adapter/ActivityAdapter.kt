package com.qualifications.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.qualifications.R
import com.qualifications.model.Activity

class ActivityAdapter : RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.activity_name)
        val note: TextView = itemView.findViewById(R.id.activity_note)
        val percent: TextView = itemView.findViewById(R.id.activity_percent)
        val button: AppCompatImageButton = itemView.findViewById(R.id.activity_delete_button)
    }

    var activities = ArrayList<Activity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activity = activities[position]

        holder.name.text = activity.name
        holder.note.text = "Note: ${activity.note}"
        holder.percent.text = "Percent: ${activity.percent * 100}"
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