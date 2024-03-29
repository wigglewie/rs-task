package com.gmail.wigglewie.rstask5.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gmail.wigglewie.rstask5.R
import kotlinx.android.synthetic.main.fragment_image_view.image_view_image

class ImageViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_view, container, false)
        val imageViewImage: ImageView = view.findViewById(R.id.image_view_image)
        val catLink = arguments?.getString("link")
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this)
            .load(catLink)
            .into(imageViewImage)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_image_view, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                val drawable = image_view_image.drawable
                if (drawable != null) {
                    if (drawable.isFilterBitmap) {
                        val bitmap = (drawable as BitmapDrawable).bitmap
                        MediaStore.Images.Media.insertImage(
                            context?.contentResolver,
                            bitmap,
                            "cat", "cat's picture"
                        )
                        Toast.makeText(
                            context,
                            getString(R.string.saved_to_gallery),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            getString(R.string.no_gif_files_allowed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.loading),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
