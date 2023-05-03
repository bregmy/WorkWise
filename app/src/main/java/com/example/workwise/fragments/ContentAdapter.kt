package com.example.workwise.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workwise.R

class ContentAdapter(private val Items: ArrayList<ContentClass>): RecyclerView.Adapter<ContentAdapter.ViewHolder>(){

    private var longClickListener: OnLongClickListener? = null

    interface OnLongClickListener {
        fun onItemLongClicked(position: Int)
    }

    fun setOnLongClickListener(listener: OnLongClickListener) {
        longClickListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.compose_item, parent, false)
        return ViewHolder(itemView, longClickListener)
    }

    class ViewHolder(itemView: View, private val longClickListener: OnLongClickListener?) : RecyclerView.ViewHolder(itemView) {
        val postView: TextView = itemView.findViewById(R.id.post_titles)
        val contentView: TextView = itemView.findViewById(R.id.post_content)

        init {
            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    longClickListener?.onItemLongClicked(position)
                }
                true
            }
        }
    }

    override fun onBindViewHolder(holder: ContentAdapter.ViewHolder, position: Int) {
        val item = Items[position]
        // Set item views based on views and data model
        holder.postView.text = item.Post
        holder.contentView.text = item.Content
    }

    override fun getItemCount(): Int {
        return Items.size
    }
    // handle long press event


}


