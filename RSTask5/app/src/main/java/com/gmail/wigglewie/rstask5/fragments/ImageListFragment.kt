package com.gmail.wigglewie.rstask5.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.wigglewie.rstask5.R
import com.gmail.wigglewie.rstask5.adapters.CatAdapter
import com.gmail.wigglewie.rstask5.data.Cat
import com.gmail.wigglewie.rstask5.data.CatServiceBuilder
import com.gmail.wigglewie.rstask5.data.CatsEndpoints
import kotlinx.android.synthetic.main.fragment_image_list.recyclerView
import retrofit2.Call
import retrofit2.Callback

class ImageListFragment : Fragment() {

    var cats = listOf<Cat>()
    var isFirstLoad = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_image_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        recyclerView.apply { layoutManager = GridLayoutManager(context, 2) }
        recyclerView.setHasFixedSize(false)

        drawCats()

        recyclerView.onScrollToEnd {
            loadCats()
            val onSaveInstanceState = recyclerView.layoutManager?.onSaveInstanceState()
            drawCats()
            recyclerView.layoutManager?.onRestoreInstanceState(onSaveInstanceState)
        }
    }

    private fun drawCats() {

        recyclerView.apply {
            recyclerView.adapter = CatAdapter(cats) { item ->
                val bundle = Bundle()
                bundle.putString("link", item.url)
                val fragment = ImageViewFragment()
                fragment.arguments = bundle

                fragmentManager?.beginTransaction()
                    ?.setCustomAnimations(
                        R.anim.card_flip_right_in,
                        R.anim.card_flip_right_out,
                        R.anim.card_flip_left_in,
                        R.anim.card_flip_left_out
                    )
                    ?.replace(R.id.container, fragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }
    }

    private fun loadCats() {

        val request = CatServiceBuilder.buildService(CatsEndpoints::class.java)
        val call = request.getCats()

        call.enqueue(object : Callback<List<Cat>> {

            override fun onResponse(
                call: Call<List<Cat>>,
                response: retrofit2.Response<List<Cat>>
            ) {
                if (response.isSuccessful) {
                    cats = cats + response.body()!!
                    if (isFirstLoad) {
                        drawCats()
                        isFirstLoad = false
                    } else {
                        Toast.makeText(
                            context,
                            getString(R.string.toast_loaded_more_cats),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun RecyclerView.onScrollToEnd(
        onScrollNearEnd: (Unit) -> Unit
    ) = addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (!recyclerView.canScrollVertically(1)) {
                onScrollNearEnd(Unit)
            }
        }
    })

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadCats()
    }
}
