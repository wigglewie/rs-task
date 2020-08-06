package com.gmail.wigglewie.rstask6.view

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.gmail.wigglewie.rstask6.R
import com.gmail.wigglewie.rstask6.contract.ItemViewActivityContract
import com.gmail.wigglewie.rstask6.data.DataItem
import com.gmail.wigglewie.rstask6.presenter.ItemViewActivityPresenter
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_layout
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_textDescription
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_textSpeaker
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_textTitle
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_textVideoDuration
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_video
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_videoPreview
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_video_play_button

class ItemViewActivity : AppCompatActivity(), ItemViewActivityContract.View {

    private var presenter: ItemViewActivityPresenter? = null
    private var exoPlayer: SimpleExoPlayer? = null
    private var item: DataItem? = null
    private var mode: Boolean = false
    private var videoSource: MediaSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_item_with_video)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        item = intent.getSerializableExtra("item") as DataItem
        mode = intent.getBooleanExtra("mode", false)
        presenter = ItemViewActivityPresenter(this, item!!, mode)
    }

    override fun initPlayer() {
        val vidAddress = Uri.parse(item?.videoUrl)
        exoPlayer = SimpleExoPlayer.Builder(this).build()
        viewItem_video.player = exoPlayer

        val dataSourceFactory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, "RSTask6")
        )
        videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(vidAddress) as MediaSource

        viewItem_video_play_button.setOnClickListener {
            presenter?.onPlayButtonClicked()
        }
    }

    override fun playButtonClicked() {
        exoPlayer?.prepare(videoSource!!, true, false)
        viewItem_videoPreview.visibility = View.GONE
        viewItem_textVideoDuration.visibility = View.GONE
        viewItem_video_play_button.visibility = View.GONE
    }

    override fun initView(item: DataItem?, mode: Boolean) {
        Glide.with(viewItem_videoPreview.context)
            .load(item?.imageUrl)
            .into(viewItem_videoPreview)
        viewItem_textVideoDuration.text = item?.videoDuration
        viewItem_textTitle.text = item?.title
        viewItem_textSpeaker.text = item?.speaker
        viewItem_textDescription.text = item?.description

        if (mode) {
            viewItem_layout.setBackgroundResource(R.color.nightModeColorBackground)
            viewItem_textTitle.setTextColor(resources.getColor(R.color.nightModeColorTitle))
            viewItem_textSpeaker.setTextColor(resources.getColor(R.color.nightModeColorSpeaker))
            viewItem_textDescription.setTextColor(resources.getColor(R.color.nightModeColorDescription))
        } else {
            viewItem_layout.setBackgroundResource(R.color.dayModeColorBackground)
            viewItem_textTitle.setTextColor(resources.getColor(R.color.dayModeColorTitle))
            viewItem_textSpeaker.setTextColor(resources.getColor(R.color.dayModeColorSpeaker))
            viewItem_textDescription.setTextColor(resources.getColor(R.color.dayModeColorDescription))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        exoPlayer = null
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
