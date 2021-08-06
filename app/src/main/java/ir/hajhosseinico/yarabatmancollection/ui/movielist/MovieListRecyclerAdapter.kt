package ir.hajhosseinico.yarabatmancollection.ui.movielist


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.hajhosseinico.yarabatmancollection.R
import ir.hajhosseinico.yarabatmancollection.model.retrofit.responsemodels.MovieListNetworkEntity

/**
 * Movie list recycler adapter
 * Using Diff Util for better performance
 */
class MovieListRecyclerAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieListNetworkEntity>() {

        override fun areItemsTheSame(
            oldItem: MovieListNetworkEntity,
            newItem: MovieListNetworkEntity
        ): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(
            oldItem: MovieListNetworkEntity,
            newItem: MovieListNetworkEntity
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviePostViewHolder(

            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_movie_list_item,
                parent,
                false
            ),
            interaction

        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is MoviePostViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<MovieListNetworkEntity>) {
        differ.submitList(list)
    }

    class MoviePostViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MovieListNetworkEntity) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            // need to shrink images b/c they are very high resolution
            val requestOptions = RequestOptions
                .overrideOf(1920, 1080)
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(item.poster)
                .placeholder(R.drawable.ic_placeholder)
                .into(itemView.findViewById(R.id.imgMovieBanner))

            itemView.findViewById<TextView>(R.id.txtMovieTitle).text = item.title
            itemView.findViewById<TextView>(R.id.txtMovieYear).text = item.year
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: MovieListNetworkEntity)
    }
}


