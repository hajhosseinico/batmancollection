package ir.hajhosseinico.yarabatmancollection.ui.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.hajhosseinico.yarabatmancollection.R
import ir.hajhosseinico.yarabatmancollection.databinding.FragmentMovieListBinding
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.MovieListNetworkEntity
import ir.hajhosseinico.yarabatmancollection.ui.MainActivity
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.DataState
import ir.hajhosseinico.yarabatmancollection.util.TopSpacingItemDecoration

/**
 * MovieListFragment
 */
@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list),
    MovieListRecyclerAdapter.Interaction {

    private val viewModel: MovieListViewModel by viewModels()
    lateinit var movieListRecyclerAdapter: MovieListRecyclerAdapter
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val apiKey = "3e974fca"
    private val name = "batman"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // setting view binding
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setting actionbar
        setActionbar(activity as MainActivity, getString(R.string.movie_list_fragment_title))
        // instantiating recyclerView
        initRecyclerView()
        // subscribing observer
        subscribeObservers()
        viewModel.setStateEvent(MovieListStateEvent.GetMovies, apiKey, name)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<MovieListNetworkEntity>> -> {
                    // getting response and handling it
                    displayProgressBar(false)
                    movieListRecyclerAdapter.submitList(dataState.data)
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

    // recyclerView onItemClickListener
    override fun onItemSelected(position: Int, item: MovieListNetworkEntity) {
        val bundle = Bundle()
        bundle.putString("imdbID", item.imdbID)
        bundle.putString("title", item.title)
        bundle.putString("apiKey", apiKey)
        findNavController().navigate(R.id.action_mainFragment_to_movieDetailFragment, bundle)
    }

    // init recycler view and set onItemClickListener to this
    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MovieListFragment.context, 3)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            movieListRecyclerAdapter = MovieListRecyclerAdapter(this@MovieListFragment)
            adapter = movieListRecyclerAdapter
        }
    }

    // notify that internet and cache are not available
    private fun displayError(message: String?) {
        Toast.makeText(context, "Internet and cached data are not available", Toast.LENGTH_LONG)
            .show()
        Log.d("LOG", message ?: "")
    }

    // show or hide progressbar
    private fun displayProgressBar(shouldDisplay: Boolean) {
        view?.findViewById<ProgressBar>(R.id.prg_loading)!!.visibility =
            if (shouldDisplay) View.VISIBLE else View.GONE
    }

    // set actionbar
    private fun setActionbar(activity: MainActivity, title: String) {
        activity.supportActionBar?.show()
        activity.supportActionBar?.title = title
        activity.supportActionBar?.setIcon(R.drawable.ic_action_bar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false);
        activity.supportActionBar?.setDisplayUseLogoEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    // remove view binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}