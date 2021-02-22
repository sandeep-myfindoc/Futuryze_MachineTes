package com.futuryze.view.HourlyTempListView

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.futuryze.R
import com.futuryze.databinding.LayoutSubitemTempBinding

import com.futuryze.model.topRateMoviesList.HourlyTemperatureModel


class HourlyTempListAdapter : PagedListAdapter<HourlyTemperatureModel, HourlyTempListAdapter.HourlyTempViewHolder> {
    private var mContext: Context? = null
    private var emptyLabel: String? = null

    constructor(mContext: Context?) : super(USER_COMPARATOR) {
        this.mContext = mContext
        emptyLabel = "__"
    }

    protected constructor(diffCallback: DiffUtil.ItemCallback<HourlyTemperatureModel?>) : super(diffCallback) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyTempViewHolder {
        var binding: LayoutSubitemTempBinding
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.layout_subitem_temp,
            parent,
            false
        )
        return HourlyTempViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyTempViewHolder, position: Int) {
        var topRatedMovie: HourlyTemperatureModel? = getItem(position)
        holder.binding.model = topRatedMovie

    }

    inner class HourlyTempViewHolder(var binding: LayoutSubitemTempBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            /*binding.getRoot().setOnClickListener(View.OnClickListener {
                val position: Int = getAdapterPosition()
                if (position != RecyclerView.NO_POSITION) {
                    val selectedMovie: HourlyTemperatureModel? = getItem(position)
                    val intent = Intent(mContext, HourlyTemperatureModelDetail::class.java)
                    var key: String? = null
                    var value: String? = null
                    key = "movie"
                    value = Gson().toJson(selectedMovie, HourlyTemperatureModel::class.java)
                    intent.putExtra(SharedPreferencesName.TOP_RATED_MOVIE_DETAIL, value)
                    mContext?.startActivity(intent)
                }
            })*/
            /*binding.imgLike.setOnClickListener(View.OnClickListener {
                val position: Int = getAdapterPosition()
                if (position != RecyclerView.NO_POSITION) {
                    var selectedMovie: HourlyTemperatureModel? = getItem(position)
                    val wishlist = Wishlist()
                    wishlist.movieId = selectedMovie!!.id
                    wishlist.posterPath = selectedMovie!!.poster_path
                    wishlist.movieTitle = selectedMovie!!.title
                    wishlist.releaseDate = selectedMovie!!.release_date
                    if (WishListDatabase.getInstance(mContext)?.wishListDao()?.isWish(selectedMovie!!.id) != 1) {
                        binding.imgLike.setImageResource(R.drawable.like)
                        WishListDatabase.getInstance(mContext)?.wishListDao()?.addTowishdata(wishlist)
                    } else {
                        binding.imgLike.setImageResource(R.drawable.dislike)
                        WishListDatabase.getInstance(mContext)?.wishListDao()?.delete(wishlist)
                    }
                }
            })*/
        }

    }

    companion object {
        private val USER_COMPARATOR: DiffUtil.ItemCallback<HourlyTemperatureModel> =
            object : DiffUtil.ItemCallback<HourlyTemperatureModel>() {
                override fun areItemsTheSame(
                    oldItem: HourlyTemperatureModel,
                    newItem: HourlyTemperatureModel
                ): Boolean {
                    return oldItem.temp == newItem.temp
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: HourlyTemperatureModel,
                    newItem: HourlyTemperatureModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}


