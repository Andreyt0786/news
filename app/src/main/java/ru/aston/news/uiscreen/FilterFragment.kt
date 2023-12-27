package ru.aston.news.uiscreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.datepicker.MaterialDatePicker
import ru.aston.news.App
import ru.aston.news.R
import ru.aston.news.databinding.FragmentFiltersBinding
import ru.aston.news.dto.MainEvent
import ru.aston.news.viewModel.FilterViewModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class FilterFragment : Fragment() {

    @Inject
    lateinit var viewModel: FilterViewModel

    private var binding: FragmentFiltersBinding? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiltersBinding.inflate(inflater, container, false)



        binding?.topAppBar?.setNavigationOnClickListener {
            viewModel.navigateBack()
        }


        binding!!.toggleGroup.addOnButtonCheckedListener { toggleButton, checkId, isChecked ->

            if (isChecked) {
                when (checkId) {
                    R.id.popular -> {
                        viewModel.send(MainEvent.SaveRelevant("popularity"))
                        Log.d("LiveData", "popularity")
                    }

                    R.id.newButton -> {
                        viewModel.send(MainEvent.SaveRelevant("publishedAt"))
                        Log.d("LiveData", "new")
                    }

                    R.id.relevant -> {
                        viewModel.send(MainEvent.SaveRelevant("relevant"))
                        Log.d("LiveData", "relevancy")
                    }
                }
            }
        }

        binding!!.radioGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rus -> {
                    viewModel.send(MainEvent.SaveLanguage("ru"))
                    Log.d("LiveData", "rus")
                    viewModel.saveLan("ru")
                }

                R.id.en -> {
                    viewModel.send(MainEvent.SaveLanguage("en"))
                    Log.d("LiveData", "en")
                    viewModel.saveLan("en")
                }

                R.id.de -> {
                    viewModel.send(MainEvent.SaveLanguage("de"))
                    Log.d("LiveData", "de")
                    viewModel.saveLan("de")
                }

                else -> {
                    viewModel.send(MainEvent.SaveLanguage(null))
                    Log.d("LiveData", "null")
                }
            }
        }

        binding?.topAppBar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.checked -> {
                    viewModel.navigateBack()
                    true
                }

                else -> false
            }
        }

        setupData()

        viewModel.state.observe(viewLifecycleOwner) { state ->
            updateLanguagePosition(state?.language)

        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            updateRelevantPosition(state?.relevant)
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            if (!state?.dateFrom.isNullOrEmpty()) {
                binding!!.tab.visibility = View.GONE
                binding!!.istab.visibility = View.VISIBLE
                binding!!.dataText.visibility = View.GONE
                binding!!.dataTextBlue.visibility = View.VISIBLE
                val dataFrom = state?.startTime?.substring(0,6)
                val year = state?.dateFrom?.substring(0, 4)
                val dataTo = state!!.endTime?.substring(0,6)
                binding!!.dataTextBlue.text = "$dataFrom-$dataTo,$year"
            } else {
                binding!!.istab.visibility = View.GONE
                binding!!.tab.visibility = View.VISIBLE
                binding!!.dataText.visibility = View.VISIBLE
                binding!!.dataTextBlue.visibility = View.GONE
            }
        }



        return binding?.root
    }

    private fun updateLanguagePosition(position: String?) {
        when (position) {

            "ru" -> binding!!.radioGroup.check(R.id.rus)
            "en" -> binding!!.radioGroup.check(R.id.en)
            "de" -> binding!!.radioGroup.check(R.id.de)
        }
    }

    private fun updateRelevantPosition(position: String?) {
        when (position) {
            "popularity" -> binding!!.toggleGroup.check(R.id.popular)
            "publishedAt" -> binding!!.toggleGroup.check(R.id.newButton)
            "relevant" -> binding!!.toggleGroup.check(R.id.relevant)
        }
    }

    fun setupData() = with(binding) {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(getString(R.string.select_data))
            //.setTheme(com.google.android.material.R.style.ThemeOverlay_Material3_MaterialCalendar)
            .setTheme(R.style.ThemeOverlay_App_DatePicker)
            .build()

        binding?.tab?.setOnClickListener {
            datePicker.show(childFragmentManager, null)
        }

        binding?.istab?.setOnClickListener {
            datePicker.show(childFragmentManager, null)
        }

        datePicker.addOnPositiveButtonClickListener { selection ->
            val startCalendar = Calendar.getInstance(TimeZone.getDefault()).apply {
                timeInMillis = selection.first

            }
            val myFormat = SimpleDateFormat("yyyy-MM-dd")
            val format = SimpleDateFormat("MMMdd yyyy | HH:mm")
            val start = myFormat.format(startCalendar.getTime())

            val startTime = format.format(startCalendar.getTime())
            val endCalendar = Calendar.getInstance(TimeZone.getDefault()).apply {
                timeInMillis = selection.second
            }
            val end = myFormat.format(endCalendar.getTime())
            val endTime = format.format(endCalendar.getTime())

            viewModel.send(
                MainEvent.SaveTime(start, end, startTime, endTime)
            )
            Log.d("LiveData", "$startTime")
        }

        datePicker.addOnNegativeButtonClickListener {
            MainEvent.SaveTime(null, null, null, null)
        }
    }
}
