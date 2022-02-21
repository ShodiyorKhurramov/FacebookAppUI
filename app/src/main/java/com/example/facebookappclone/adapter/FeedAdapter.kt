package com.example.facebookappclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebookappclone.R
import com.example.facebookappclone.activity.MainActivity
import com.example.facebookappclone.model.Feed
import com.example.facebookappclone.model.Story

class FeedAdapter(var activity: MainActivity, var items: ArrayList<Feed>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ITEM_HEAD = 0
        private const val TYPE_ITEM_STORY = 1
        private const val TYPE_ITEM_POST = 2
    }

    override fun getItemViewType(position: Int): Int {
        val feed = items[position]
        return when {
            feed.isHeader -> TYPE_ITEM_HEAD
            feed.stories.size > 0 -> TYPE_ITEM_STORY
            else -> TYPE_ITEM_POST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_ITEM_HEAD -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_feed_head, parent, false)
                HeadViewHolder(activity, view)
            }
            TYPE_ITEM_STORY -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_feed_story, parent, false)
                StoryViewHolder(activity, view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_feed_post, parent, false)
                PostViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = items[position]

        if (holder is StoryViewHolder) {
            val recyclerView = holder.recyclerView
            val stories = feed.stories
            refreshAdapter(recyclerView, stories)
        }

        if (holder is PostViewHolder) {
            Glide.with(activity).load(feed.post!!.profile).centerCrop().into(holder.ivProfile)
            Glide.with(activity).load(feed.post!!.photo).fitCenter().into(holder.ivPost)
            holder.tvFullName.text = feed.post!!.fullName
        }

        if (holder is HeadViewHolder) {
            holder.tv_link.setOnClickListener{
                activity.openLinkActivty()
            }
            Glide.with(activity).load(feed.profile).centerCrop().into(holder.ivProfile)
        }
    }

    private fun refreshAdapter(recyclerView: RecyclerView, stories: ArrayList<Story>) {
        val adapter = StoryAdapter(activity, stories)
        recyclerView.adapter = adapter
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProfile: ImageView = view.findViewById(R.id.iv_profile)
        val tvFullName: TextView = view.findViewById(R.id.tv_fullName)
        val ivPost: ImageView = view.findViewById(R.id.iv_post)
    }

    class StoryViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        init {
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    class HeadViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        val ivProfile: ImageView = view.findViewById(R.id.iv_profile)
        val tv_link : TextView = view.findViewById(R.id.tv_link)
    }
}