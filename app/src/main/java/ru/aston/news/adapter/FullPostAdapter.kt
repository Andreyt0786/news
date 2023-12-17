package ru.aston.news.adapter

import android.net.Uri
import ru.aston.news.databinding.FragmentSinglePostBinding
import android.os.Build
import android.text.SpannableString
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.aston.news.R
import ru.aston.news.dto.Post
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


interface InInteractionListener {
    fun showWall(post: Post) {}
}


class FullPostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}


class FullPostAdapter(
    private val onInteractionListener: InInteractionListener,
) : ListAdapter<Post, FullPostViewHolder>(FullPostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullPostViewHolder {
        val binding = FragmentSinglePostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FullPostViewHolder(binding, onInteractionListener)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: FullPostViewHolder, position: Int) {
        val post = getItem(position) ?: return
        holder.bind(post)
    }
}

class FullPostViewHolder(
    private val binding: FragmentSinglePostBinding,
    private val onInteractionListener: InInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {


    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(post: Post) {

        val actual = OffsetDateTime.parse(post.publishedAt, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern(" MMMM dd yyyy | HH:mm")
        val formatDateTime = actual.format(formatter)

        val length:Int = post.content?.length!!
        val startPosition = length - 100
        val spanned = SpannableString(post.content)
        val urlDestination = post.url
        spanned.setSpan(Uri.parse(urlDestination),startPosition,length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE )

        binding.apply {
            headline.text = post.title
            text.text = post.source.name
            data.text = formatDateTime
            description.text = spanned

          /*  if (post.urlToImage.isNullOrEmpty()) {
                binding.thumbnail.setImageResource(R.mipmap.noimageavailable)
            } else {
                val url = post.urlToImage
                Glide.with(binding.thumbnail)
                    .load(url)
                    .timeout(10000)
                    .into(binding.thumbnail)
            }*/
        }
    }
}

