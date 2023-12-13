package ru.aston.news.adapter.sourcePost


import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.aston.news.R
import ru.aston.news.databinding.CardSourcePostBinding
import ru.aston.news.dto.SourcePost


interface InteractionListener {

    fun showWall(sourcePost: SourcePost) {}
}


class PostDiffCallback : DiffUtil.ItemCallback<SourcePost>() {
    override fun areItemsTheSame(oldItem: SourcePost, newItem: SourcePost): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SourcePost, newItem: SourcePost): Boolean {
        return oldItem == newItem
    }
}


class SourcePostAdapter(
    private val onInteractionListener: InteractionListener,
) : ListAdapter<SourcePost, SourcePostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcePostViewHolder {
        val binding =
            CardSourcePostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SourcePostViewHolder(binding, onInteractionListener)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: SourcePostViewHolder, position: Int) {
        val post = getItem(position) ?: return
        holder.bind(post)
    }
}

class SourcePostViewHolder(
    private val binding: CardSourcePostBinding,
    private val onInteractionListener: InteractionListener,
) : RecyclerView.ViewHolder(binding.root) {


    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(sourcePost: SourcePost) {

        val category = sourcePost.category.capitalize()
        val country = sourcePost.country?.toUpperCase()
        binding.apply {
            companyName.text = sourcePost.name
            text.text = category + " | " + country


            val urlava: Int
            when (sourcePost.name) {
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

            companyName.setOnClickListener {
                onInteractionListener.showWall(sourcePost)
            }

            text.setOnClickListener {
                onInteractionListener.showWall(sourcePost)
            }

            avatar.setOnClickListener {
                onInteractionListener.showWall(sourcePost)
            }

        }
    }
}

