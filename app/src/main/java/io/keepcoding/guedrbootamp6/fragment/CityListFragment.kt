package io.keepcoding.guedrbootamp6.fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import io.keepcoding.guedrbootamp6.R
import io.keepcoding.guedrbootamp6.model.Cities
import io.keepcoding.guedrbootamp6.model.City
import kotlinx.android.synthetic.main.fragment_city_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [CityListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CityListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = CityListFragment()
    }

    private var onCitySelectedListener: OnCitySelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cities = Cities()

        val adapter = ArrayAdapter<City>(
                activity,
                android.R.layout.simple_list_item_1,
                cities.toArray())

        city_list.adapter = adapter

        // city_list.setOnItemClickListener { adapterView, view, index, l -> }
        city_list.setOnItemClickListener { _, _, index, _ ->
            onCitySelectedListener?.onCitySelected(cities[index], index)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonOnAttach(context as Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonOnAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun commonOnAttach(activity: Activity?) {
        if (activity is OnCitySelectedListener) {
            onCitySelectedListener = activity
        } else {
            onCitySelectedListener = null
        }
    }

    interface OnCitySelectedListener {
        fun onCitySelected(city: City, position: Int)
    }
}
