package com.buyin.dalili.features.map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.buyin.dalili.R
import com.buyin.dalili.databinding.FragmentMapBinding


class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding


    private val listLocation = listOf(
        Location("32.10591978358647", "36.18492991652212"),
        Location("32.101272885908806","36.18681165839935"),
        Location("32.105368049088725", "36.18564965270754"),
        Location("32.10602461276418", "36.185659422882004"),
        Location("32.10591978358647", "36.18492991652212"),
        Location("32.10202639984217", "36.188904722909875"),
        Location("32.1047298335109", "36.185676653072186"),
        Location("32.10156119595108", "36.18471055028301"),
        Location("32.102429100428964", "36.18960496151593"),
        Location("32.10709191178797", "36.184478774743745"),
        Location("32.1059165594355", "36.18489344551278"),
        Location("32.102660361809846", "36.18799067594852"),
        Location("32.10028063340833", "36.18878271348739")
    )

    var current = Location("", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        startGoogleMaps(
//            getString(
//                R.string.google_maps_direction_link,
//                "32.0751309",
//               "36.0229854"
//            )
//        )
        initView()

    }

    private fun initView() {
        val feelings = resources.getStringArray(R.array.feelings)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.edit_text_drow, feelings)

        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            current = listLocation[position]
        }

        binding.buttonGoing.setOnClickListener {
            startGoogleMaps(
                getString(
                    R.string.google_maps_direction_link,
                    current.latitude,
                    current.longitude
                )
            )
        }
    }


}

fun Fragment.startGoogleMaps(directionUrl: String?) {
    if (directionUrl.isNullOrEmpty()) return

    val intent = Intent(
        Intent.ACTION_VIEW, Uri.parse(directionUrl)
    )
    startActivity(intent)

}

data class Location(
    val latitude: String, val longitude: String
)