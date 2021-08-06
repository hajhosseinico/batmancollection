package ir.hajhosseinico.yarabatmancollection.ui.splash

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import ir.hajhosseinico.yarabatmancollection.R
import ir.hajhosseinico.yarabatmancollection.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideActionBar()

        Glide.with(this)
            .load(Uri.parse("file:///android_asset/splash.gif"))
            .into(binding.imgSplash);

        CoroutineScope(IO).launch {
            delay(3000)
            navigateToMovieListFragment()
        }
    }

    private suspend fun navigateToMovieListFragment() {
        withContext(Main) {
            val navBuilder = NavOptions.Builder()
            val navOptions = navBuilder.setPopUpTo(R.id.splashFragment, true).build()
            findNavController().navigate(
                R.id.action_splashFragment_to_movieListFragment,
                null,
                navOptions
            )
        }
    }

    private fun hideActionBar() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}