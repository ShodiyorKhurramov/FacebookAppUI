package com.example.facebookappclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebookappclone.R
import com.example.facebookappclone.model.Story

class StoryAdapter(var context: Context, var items: ArrayList<Story>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==0){
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_story_view_create, parent, false)
            return StoryOneViewHolder(view)
        }
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_story_view, parent, false)
        return StoryViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        if(position==0){
            return 0
        }
      return  position
    }

    class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProfile: ImageView = view.findViewById(R.id.iv_profile)
        val tvFullName: TextView = view.findViewById(R.id.tv_fullName)
        val ivBackground: ImageView = view.findViewById(R.id.iv_background)
    }

    class StoryOneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProfile : ImageView = view.findViewById(R.id.iv_profile)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story = items[position]
        if (holder is StoryViewHolder) {
            holder.tvFullName.text = story.fullName
            Glide.with(context).load(story.profile).centerCrop().into(holder.ivProfile)
            Glide.with(context).load(story.story).centerCrop().into(holder.ivBackground)
        }
        if(holder is StoryOneViewHolder){
            Glide.with(context).load(story.profile).centerCrop().into(holder.ivProfile)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}