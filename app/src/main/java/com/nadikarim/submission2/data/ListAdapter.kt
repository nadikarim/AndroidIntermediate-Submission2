package com.nadikarim.submission2.data

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nadikarim.submission2.data.model.stories.Story
import com.nadikarim.submission2.databinding.ListStoryBinding
import com.nadikarim.submission2.ui.detail.DetailActivity
import com.nadikarim.submission2.utils.EXTRA_DESCRIPTION
import com.nadikarim.submission2.utils.EXTRA_IMAGE
import com.nadikarim.submission2.utils.STORY_NAME

class ListAdapter : PagingDataAdapter<Story, ListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class ViewHolder(private val binding: ListStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(story.photoUrl)
                    .into(imageView)

                tvJudul.text = story.name
                tvDesc.text = story.description

                cardView.setOnClickListener {

                    val intent = Intent(itemView.context, DetailActivity::class.java)

                    intent.putExtra(EXTRA_IMAGE, story.photoUrl)
                    intent.putExtra(EXTRA_DESCRIPTION, story.description)
                    intent.putExtra(STORY_NAME, story.name)

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(imageView, "photo"),
                            Pair(tvJudul, "name"),
                            Pair(tvDesc, "description"),
                        )
                    itemView.context.startActivity(intent, optionsCompat.toBundle())
                }

            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}