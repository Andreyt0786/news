package ru.aston.news.uiscreen

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import ru.aston.news.App
import ru.aston.news.R
import ru.aston.news.databinding.FragmentSinglePostBinding
import ru.aston.news.dto.Post
import ru.aston.news.viewModel.CheckSourceViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class SinglePostFragment : Fragment() {

    private var binding: FragmentSinglePostBinding? = null

    private val title: String
        get() = arguments?.getString(EXTRA_TITLE)!!


    @Inject
    lateinit var viewModel: CheckSourceViewModel

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
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SourceFragment", "title")
        val posts = viewModel.listPost
        val post = posts.find { it.title == title }




        Log.d("SourceFragment", "$title")
        val time = "2023-12-16T22:22:05Z"
        val actual = OffsetDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME)
        val formatter = DateTimeFormatter.ofPattern(" MMMM dd yyyy | HH:mm")
        val formatDateTime = actual.format(formatter)

        /* val length: Int = post?.content?.length!!
         val startPosition = length - 100
         val spanned = SpannableString(post?.content)
         val urlDestination = post?.url
         spanned.setSpan(
             Uri.parse(urlDestination),
             startPosition,
             length,
             Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
         )*/

        binding?.apply {
            headline.text = "Test Post"//post?.title
            text.text = "Test Post"//post?.source!!.name
            data.text = formatDateTime//post?.publishedAt
            description.text =
                "Washington, DCCNN — US consumer confidence worsened in April as Americans become more pessimistic about the job market. The Conference Board’s Consumer Confidence Index, which measures attitudes toward the economy and the job market, fell to 101.3 in April, down from 104 in March and marking the lowest level since July 2022. The business group’s measure of economic expectations fell in April and has remained below a threshold “associated with a recession within the next year” for every month since February 2022, with the exception of an uptick in December. Consumer attitudes have held steady since the turbulence in the banking industry last month, but high inflation and economic uncertainty have continued to weigh on consumers. “Consumers became more pessimistic about the outlook for both business conditions and labor markets,” said Ataman Ozyildirim, senior director of economics at The Conference Board, in a statement accompanying the data. “Compared to last month, fewer households expect business conditions to improve and more expect worsening of conditions in the next six months. They also expect fewer jobs to be available over the short term.” That matches government figures showing the labor market has begun to show some cracks. Employers added 236,000 jobs in March, the smallest gain in two years, and job openings fell below 10 million for the first time since May 2021. Large companies have continued to announce layoffs, such as 3M, which announced on Tuesday that it is cutting 6,000 jobs. The April survey showed that worries about the economy slipping into a recession persisted last month. Economists, including those at the Federal Reserve, expect a recession later in the year as the Fed’s rate hikes take a deeper hold. The share of consumers expecting more jobs to be available fell to 12.5% in April, down from 15.5% in March, while the share who anticipate fewer jobs increased to 21% from 20.5% during the same period."//spanned

            /*  if (post?.urlToImage.isNullOrEmpty()) {
                  binding!!.thumbnail.setImageResource(R.mipmap.noimageavailable)
              } else {
                  val url = post?.urlToImage*/
            Glide.with(binding!!.thumbnail)
                .load(R.mipmap.noimageavailable)
                .timeout(10000)
                .into(binding!!.thumbnail)
        }
    }


    companion object {
        private const val EXTRA_TITLE = "extra_title"

        fun getNewInstance(title: String): SinglePostFragment {
            return SinglePostFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_TITLE, title)
                }
            }
        }
    }
}