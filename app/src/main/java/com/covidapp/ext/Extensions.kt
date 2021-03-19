package com.covidapp.ext

import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.material.textview.MaterialTextView

/**
 * @author emre.memis@ovidos.com
 */

object Extensions {
    /**
     * this function works after the time you set has elapsed.
     * @param mills for manage process time
     * @param action runnable function
     */
    fun runOnHandler(mills: Long, action: () -> Unit) {
        Handler(Looper.getMainLooper())
            .postDelayed(action, mills)
    }

    /**
     * we load images using this function
     * @see com.covidapp.R.layout.layout_news
     * @param view which image is loading
     * @param url fetching remote data
     */
    @BindingAdapter("android:setRoundedImageUrl")
    @JvmStatic
    fun setRoundedImageUrl(view: AppCompatImageView, url: String?) {
        url?.let {
            view.load(it) {
                placeholder(
                    CircularProgressDrawable(view.context)
                        .apply {
                            centerRadius = 40f
                            strokeWidth = 8f
                        }
                )
                transformations(RoundedCornersTransformation(20f))
            }
        }
    }

    /**
     * we load images using this function
     * @see com.covidapp.R.layout.fragment_news_detail
     * @param view which image is loading
     * @param url fetching remote data
     */
    @BindingAdapter("android:setImageUrl")
    @JvmStatic
    fun setImageUrl(view: AppCompatImageView, url :String?) {
        url?.let {
            view.load(it) {
                placeholder(
                    CircularProgressDrawable(view.context)
                        .apply {
                            centerRadius = 60f
                            strokeWidth = 12f
                        }
                )
            }
        }
    }

    /**
     * we give a random number for the news views
     * @see com.covidapp.R.layout.layout_news
     * @param view
     */
    @BindingAdapter("android:setRandomNumber")
    @JvmStatic
    fun setRandomNumber(view: MaterialTextView, any: Any?) {
        view.text = (0..1500).random().toString()
    }
}