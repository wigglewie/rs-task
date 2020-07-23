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
import com.gmail.wigglewie.rstask5.R
import com.gmail.wigglewie.rstask5.adapters.CatAdapter
import com.gmail.wigglewie.rstask5.data.Cat
import com.gmail.wigglewie.rstask5.testingretrofit.CatServiceBuilder
import com.gmail.wigglewie.rstask5.testingretrofit.CatsEndpoints
import kotlinx.android.synthetic.main.fragment_image_list.recyclerView
import retrofit2.Call
import retrofit2.Callback

class ImageListFragment : Fragment() {

    var cats = listOf<Cat>()

//    val catViewModel = ViewModelProvider(this).get(CatViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_image_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        recyclerView.apply { layoutManager = GridLayoutManager(context, 2) }
        recyclerView.setHasFixedSize(true)

//        catViewModel.items.observe(this, Observer {
//            it ?: return@Observer
////            cats = it.toMutableList()
//        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        println()

        val request = CatServiceBuilder.buildService(CatsEndpoints::class.java)
        val call = request.getCats()

        call.enqueue(object : Callback<List<Cat>> {

            override fun onResponse(
                call: Call<List<Cat>>,
                response: retrofit2.Response<List<Cat>>
            ) {
                if (response.isSuccessful) {
                    cats = response.body()!!
                    recyclerView.adapter?.notifyDataSetChanged()
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
                    println()
                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onResume() {
        super.onResume()
        println()
    }

    override fun onDestroy() {
        super.onDestroy()
        println()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println()
    }
}
