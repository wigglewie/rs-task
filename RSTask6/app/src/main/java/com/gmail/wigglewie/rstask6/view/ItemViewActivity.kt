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
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_layout
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_textDescription
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_textSpeaker
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_textTitle
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_textVideoDuration
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_video
import kotlinx.android.synthetic.main.activity_view_item_with_video.viewItem_videoPreview

class ItemViewActivity : AppCompatActivity(), ItemViewActivityContract.View {

    private var presenter: ItemViewActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_item_with_video)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val item = intent.getSerializableExtra("item") as DataItem
        val mode = intent.getBooleanExtra("mode", false)
        presenter = ItemViewActivityPresenter(this, item, mode)

//        val vidAddress =
//            "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4"
        val vidAddress = Uri.parse(item.videoUrl)

//        val vidUri: Uri = Uri.parse(vidAddress)
//        viewItem_video.setVideoURI(vidUri)
//        val vidControl = MediaController(this)
//        vidControl.setAnchorView(viewItem_video)
//        viewItem_video.setMediaController(vidControl)

        viewItem_video.setVideoURI(vidAddress)
        viewItem_video.setOnPreparedListener {
            it.isLooping
            viewItem_videoPreview.visibility = View.GONE
            viewItem_textVideoDuration.visibility = View.GONE
            viewItem_video.start()
        }

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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
