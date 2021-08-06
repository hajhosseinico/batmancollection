package ir.hajhosseinico.yarabatmancollection.ui.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ir.hajhosseinico.yarabatmancollection.R
import ir.hajhosseinico.yarabatmancollection.databinding.FragmentMovieDetailBinding
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.MovieDetailNetworkEntity
import ir.hajhosseinico.yarabatmancollection.ui.MainActivity
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.DataState

/**
 * MovieDetailFragment
 */
@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var imdbID: String
    private lateinit var apiKey: String
    private lateinit var title: String
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // getting required data from movie list fragment
        arguments?.getString("imdbID").let { id ->
            imdbID = id!!
        }
        arguments?.getString("title").let { t ->
            title = t!!
        }
        arguments?.getString("apiKey").let { t ->
            apiKey = t!!
        }

        // setting view binding
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setting actionbar
        setActionbar(activity as MainActivity, title)
        // subscribing observer
        subscribeObservers()
        // triggering the event and passing data to view model
        viewModel.setStateEvent(MovieDetailStateEvent.GetMovieDetail,apiKey , imdbID)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<MovieDetailNetworkEntity> -> {
                    // getting response and handling it
                    displayProgressBar(false)
                    setMovieDetail(dataState.data)
                    if (dataState.isFromCache)
                        notifyIfDataIsFromCache()
                }
                is DataState.Error -> {
                    // getting error and handling it
                    displayProgressBar(false)
                    displayError(dataState.exception.toString())

                }

                is DataState.Loading -> {
                    // show loading
                    displayProgressBar(true)
                }
            }
        })
    }

    // notify that data is from cache
    private fun notifyIfDataIsFromCache() =
        Toast.makeText(context, "Data is retrieved from cache", Toast.LENGTH_LONG)
            .show()

    // notify that internet and cache are not available
    private fun displayError(message: String?) {
        Toast.makeText(context, "Internet and cached data are not available", Toast.LENGTH_LONG)
            .show()
        Log.d("LOG", message ?: "")
    }

    // show or hide progressbar
    private fun displayProgressBar(shouldDisplay: Boolean) {
        binding.prgLoading.visibility =
            if (shouldDisplay) View.VISIBLE else View.GONE
    }

    // set actionbar
    private fun setActionbar(activity: MainActivity, title: String) {
        activity.supportActionBar?.show()
        activity.supportActionBar?.title = title
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayUseLogoEnabled(false)
        activity.supportActionBar?.setDisplayShowHomeEnabled(false)
        setHasOptionsMenu(true)
    }

    // actionbar on back pressed
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
        this methode could be written in the viewModel (The logic part) but due to being a tiny logic, i decided to write it here
        if it was a full application, i would definitely put the logic part in a StringConvertor class (or String Builder) and called it from viewModel
        fragment should only show the data and should not being involved with any processes
     **/
    private fun setMovieDetail(movie: MovieDetailNetworkEntity) {
        binding.txtTitle.text = movie.title
        binding.txtDirectorValue.text = movie.director
        binding.txtWriterValue.text = movie.writer
        binding.txtActorValue.text = movie.actors
        binding.txtBoxOfficeValue.text = movie.boxoffice
        binding.txtProductionValue.text = movie.production
        "IMDB Votes: ${movie.imdbvotes}".also { binding.txtImdbVote.text = it }

        Glide.with(this)
            .load(movie.poster)
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.imgPoster)

        "Year: ${movie.year}\nRuntime: ${movie.runtime}\nGenre: ${movie.genre}\nLanguage: ${movie.language}\nType: ${movie.type}".also {
            binding.txtDescription.text = it
        }
        var ratingList = ""
        for (i in movie.ratings.indices) {
            ratingList += "${movie.ratings[i].source} : ${movie.ratings[i].value}"
            if (i != movie.ratings.size - 1)
                ratingList += "\n"
        }
        binding.rateImdb.rating = (movie.imdbrating?.toFloat()?.div(2)) ?: 0f
        binding.txtRatingList.text = ratingList
        binding.ttxPlotValue.text = movie.plot
    }

    // remove view binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}