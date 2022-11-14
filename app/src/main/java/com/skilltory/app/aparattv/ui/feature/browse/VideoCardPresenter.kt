package com.skilltory.app.aparattv.ui.feature.browse

import android.util.Log
import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import coil.load
import coil.size.Scale
import com.skilltory.app.aparattv.R
import com.skilltory.app.aparattv.domain.model.Video
import com.skilltory.app.aparattv.utils.Constants

class VideoCardPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {

        val imageCardView = ImageCardView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            cardType = BaseCardView.CARD_TYPE_MAIN_ONLY
            with(mainImageView) {
                val posterWidth = parent.resources.getDimension(R.dimen.poster_width).toInt()
                val posterHeight = parent.resources.getDimension(R.dimen.poster_height).toInt()
                layoutParams = BaseCardView.LayoutParams(posterWidth, posterHeight)
            }
        }
        return ViewHolder(imageCardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val video = item as Video
        with(viewHolder.view as ImageCardView) {
            val posterWidth =  resources.getDimension(R.dimen.poster_width).toInt()
            val posterHeight =  resources.getDimension(R.dimen.poster_height).toInt()

            mainImageView.load(
                uri = video.thumbUrl,
                builder = {
                    scale(Scale.FIT)
                    size(posterWidth, posterHeight)
                    allowHardware(false)
                })
            titleText = video.title
        }
        viewHolder.view.tag = item.title
        Log.i(Constants.TAG_HOME_RECYCELER, "onBindViewHolder: ${item.title}  View:-${viewHolder.view.tag} ")
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        Log.i("Recycler", "onUnbindViewHolder: View:-${viewHolder.view?.tag} ")
        with(viewHolder.view as ImageCardView) {
            mainImage = null
        }

    }
}