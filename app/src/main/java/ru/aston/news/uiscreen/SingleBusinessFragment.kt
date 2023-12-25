/*
package ru.aston.news.uiscreen


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.aston.news.App
import ru.aston.news.R
import ru.aston.news.databinding.FragmentSinglePostBinding
import ru.aston.news.viewModel.SingleBusinessViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SingleBusinessFragment : Fragment() {

    private var binding: FragmentSinglePostBinding? = null

    private val title: Int? by lazy { arguments?.getInt(EXTRA_TITLE) }
    private var isLike = false

    @Inject
    lateinit var viewModel: SingleBusinessViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSinglePostBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        Log.d("BusinessFragment", "title = $title")
        val posts = viewModel.posts
        Log.d("BusinessFragment", "$posts")
        val postL = posts.find { it.idPost == title }
        Log.d("BusinessFragment", "$postL")
        val post = title?.let(viewModel::onSetArgs)



        Log.d("BusinessFragment", "$title")
        val time = post?.publishedAt
        val actual = OffsetDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern(" MMM, dd yyyy | HH:mm")
        val formatDateTime = actual.format(formatter)

        val length: Int = post?.content?.length!!


        binding?.toolbar?.setNavigationOnClickListener {
            viewModel.navigateBack()
        }


        binding?.toolbar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.checked -> {
                   if(!isLike){
                        viewModel.like(post)
                        isLike = true
                    } else {
                        viewModel.remove(post)
                       isLike = false
                    }
                    true
                }

                else -> false
            }
        }

        clickable(post.content!!, post.url)
        binding?.apply {
            headline.text = post.title
            text.text = post.source.name
            data.text = formatDateTime


            if (post.urlToImage.isNullOrEmpty()) {
                binding!!.thumbnail.setImageResource(R.mipmap.noimageavailable)
            } else {
                val url = post.urlToImage
                Glide.with(binding!!.thumbnail)
                    .load(url)
                    .timeout(10000)
                    .into(binding!!.thumbnail)
            }
        }
    }

    companion object {
        private const val EXTRA_TITLE = "extra_title"

        fun getNewInstance(title: Int): SingleBusinessFragment {
            return SingleBusinessFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_TITLE, title)
                }
            }
        }
    }

    private fun clickable(longText: String, url: String) {
        try {
            val spanned = SpannableString(longText)

            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(p0: View) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = Color.BLACK
                    ds.isUnderlineText = false
                }
            }
            spanned.setSpan(
                clickableSpan,
                longText.length - 100,
                longText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding?.description?.text = spanned
            binding?.description?.movementMethod = LinkMovementMethod.getInstance()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

 */
package ru.aston.news.uiscreen
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.aston.news.App
import ru.aston.news.R
import ru.aston.news.databinding.FragmentSinglePostBinding
import ru.aston.news.viewModel.SingleBusinessViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SingleBusinessFragment : Fragment() {
    private var binding: FragmentSinglePostBinding? = null
    private val title: Int? by lazy { arguments?.getInt(EXTRA_TITLE) }
    @Inject
    lateinit var viewModel: SingleBusinessViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSinglePostBinding.inflate(inflater, container, false)
        return binding?.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("BusinessFragment", "title = $title")
        val posts = viewModel.posts
        Log.d("BusinessFragment", "$posts")
        val post = posts.find { it.idPost == title }
        Log.d("BusinessFragment", "$post")
        Log.d("BusinessFragment", "$title")
        val time = post?.publishedAt
        val actual = OffsetDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern(" MMM, dd yyyy | HH:mm")
        val formatDateTime = actual.format(formatter)
        val length: Int = post?.content?.length!!


        binding?.toolbar?.setNavigationOnClickListener {
            viewModel.navigateBack()
        }

        binding?.toolbar?.title = post.title
        binding?.toolbar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.checked -> {
                    viewModel.like(post)
                    true
                }
                else -> false
            }
        }
        clickable(post.content!!, post.url)
        binding?.apply {
            headline.text = post.title
            text.text = post.source.name
            data.text = formatDateTime
            if (post.urlToImage.isNullOrEmpty()) {
                binding!!.thumbnail.setImageResource(R.mipmap.noimageavailable)
            } else {
                val url = post.urlToImage
                Glide.with(binding!!.thumbnail)
                    .load(url)
                    .timeout(10000)
                    .into(binding!!.thumbnail)
            }
        }
    }
    companion object {
        private const val EXTRA_TITLE = "extra_title"
        fun getNewInstance(title: Int): SingleBusinessFragment {
            return SingleBusinessFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_TITLE, title)
                }
            }
        }
    }
    private fun clickable(longText: String, url: String) {
        try {
            val spanned = SpannableString(longText)
            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(p0: View) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                }
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = Color.BLACK
                    ds.isUnderlineText = false
                }
            }
            spanned.setSpan(
                clickableSpan,
                longText.length - 100,
                longText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding?.description?.text = spanned
            binding?.description?.movementMethod = LinkMovementMethod.getInstance()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}