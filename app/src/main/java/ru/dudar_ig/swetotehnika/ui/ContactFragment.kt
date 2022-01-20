package ru.dudar_ig.swetotehnika.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.*
import com.yandex.mapkit.map.*
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import ru.dudar_ig.swetotehnika.R

private const val ARG_PARAM2 = "param2"
private const val REQUEST = 1212

class ContactFragment : Fragment() {

    private var idName: String? = null

    private lateinit var mapYandex: MapView
    private lateinit var mapObject: MapObjectCollection
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idName = it.getString(ARG_PARAM2)
        }
        MapKitFactory.initialize(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = view.findViewById<Chip>(R.id.email1_chip)
        val tel = view.findViewById<Chip>(R.id.tel_chip)
        val tel1 = view.findViewById<Chip>(R.id.tel1_chip)

        bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.bottom_sheet_contact))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

//        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION )
//            != PERMISSION_GRANTED ) {
//            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST)
//        }

        mapYandex = view.findViewById(R.id.yandex_map)
        mapYandex.getMap().move(
            CameraPosition(Point(55.898922, 37.379034), 16.0f, 0.0f, 1.0f),
            Animation(Animation.Type.SMOOTH, 0f), null)

        mapObject = mapYandex.map.mapObjects.addCollection()

        mapObject.addPlacemark(Point(55.898922, 37.3787),
            ImageProvider.fromResource(context, R.drawable.ff))

        email.setOnClickListener {
            sendEmail()
        }
        tel.setOnClickListener {
           telCall("+79670588715")
        }
        tel1.setOnClickListener {
            telCall("+79032352324")
        }
    }

    private fun sendEmail() {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("info@swetotehnika.ru"))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, "Сообщение")
        mIntent.putExtra(Intent.EXTRA_TEXT, " ")
        try {
            startActivity(Intent.createChooser(mIntent, "Выберите почтовый клиент..."))
        }
        catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun telCall(telefon: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$telefon")

        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.CALL_PHONE )
            != PERMISSION_GRANTED ) {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST)
        }
            try {
                startActivity(intent)
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).titleText(idName!!)
        mapYandex.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        mapYandex.onStop()
        MapKitFactory.getInstance().onStop()
    }

    companion object {
        @JvmStatic
        fun newInstance(param2: String) =
            ContactFragment().apply {
                arguments = Bundle().apply { putString(ARG_PARAM2, param2)
                }
            }
    }
}