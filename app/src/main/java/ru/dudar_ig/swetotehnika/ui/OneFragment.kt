package ru.dudar_ig.swetotehnika.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.data.Tovar
import ru.dudar_ig.swetotehnika.database.CartCountVM


class OneFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    companion object {
        @JvmStatic
        fun newInstance() = OneFragment()
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).navClickable(-1)

    }
}