package ru.aston.news.adapter.post

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.aston.news.R
import ru.aston.news.dto.Post
import ru.aston.news.databinding.CardPostBinding

/*
interface OnInteractionListener {
    fun showWall(post: Post) {}
}


class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}


class PostAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

   /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }*/


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position) ?: return
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {


    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(post: Post) {


        binding.apply {
            companyName.text = post.source.name
            text.text = post.title

            /*  if(post.source.id=="cnn")
              binding.avatar.setImageResource(R.mipmap.cnn)
              */
            var urlava: Int
            when (post.source.name) {
                "CNN" -> urlava = R.mipmap.cnn
                "BBC" -> urlava = R.mipmap.bbc
                "Bloomberg" -> urlava = R.mipmap.bloomberg
                "Fox News" -> urlava = R.mipmap.foxnews
                "CNBC" -> urlava = R.mipmap.cnbc
                else -> urlava = R.mipmap.noava
            }

            Glide.with(binding.avatar)
                .load(urlava)
                .timeout(10000)
                .circleCrop()
                .into(binding.avatar)



            if (post.urlToImage.isNullOrEmpty()) {
                binding.avatar.setImageResource(R.mipmap.noimageavailable)
            } else {
                val url = post.urlToImage
                Glide.with(binding.thumbnail)
                    .load(url)
                    .timeout(10000)
                    .into(binding.thumbnail)
            }

            companyName.setOnClickListener {
                onInteractionListener.showWall(post)
            }

            text.setOnClickListener {
                onInteractionListener.showWall(post)
            }

            avatar.setOnClickListener {
                onInteractionListener.showWall(post)
            }
        }
    }
}
*/

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}


class PostAdapter(private val onPostClick: (Post) -> Unit) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val holder = PostViewHolder(CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)
        holder.itemView.setOnClickListener {
            holder.item?.let(onPostClick)
        }
        return holder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position) ?: return
        holder.bind(post)
    }
}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var item: Post? = null
    private val binding = CardPostBinding.bind(itemView)

    fun bind(post: Post) {
        item = post
        binding.apply {
            companyName.text = post.source.name
            text.text = post.title

            /*  if(post.source.id=="cnn")
              binding.avatar.setImageResource(R.mipmap.cnn)
              */
            val urlava = when (post.source.name) {
                "CNN" -> R.mipmap.cnn
                "BBC" -> R.mipmap.bbc
                "Bloomberg" -> R.mipmap.bloomberg
                "Fox News" -> R.mipmap.foxnews
                "CNBC" -> R.mipmap.cnbc
                else -> R.mipmap.noava
            }

            Glide.with(binding.avatar)
                .load(urlava)
                .timeout(10000)
                .circleCrop()
                .into(binding.avatar)

            if (post.urlToImage.isNullOrEmpty()) {
                binding.avatar.setImageResource(R.mipmap.noimageavailable)
            } else {
                val url = post.urlToImage
                Glide.with(binding.thumbnail)
                    .load(url)
                    .timeout(10000)
                    .into(binding.thumbnail)
            }
        }
    }
}


