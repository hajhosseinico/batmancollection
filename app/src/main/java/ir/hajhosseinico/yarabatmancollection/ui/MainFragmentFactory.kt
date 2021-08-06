package ir.hajhosseinico.yarabatmancollection.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import ir.hajhosseinico.yarabatmancollection.ui.moviedetail.MovieDetailFragment
import ir.hajhosseinico.yarabatmancollection.ui.movielist.MovieListFragment
import ir.hajhosseinico.yarabatmancollection.ui.splash.SplashFragment
import javax.inject.Inject

/**
 * MainFragmentFactory
 */

class MainFragmentFactory
@Inject
constructor() : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return  when(className){
            MovieListFragment::class.java.name ->{
                MovieListFragment()
            }
            MovieDetailFragment::class.java.name ->{
                MovieDetailFragment()
            }
            SplashFragment::class.java.name ->{
                SplashFragment()
            }else -> super.instantiate(classLoader, className)
        }
    }
}
