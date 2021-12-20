package com.edsusantoo.moviedb.ui.marker

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.edsusantoo.moviedb.R
import com.edsusantoo.moviedb.databinding.FragmentMarkerBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import com.karumi.dexter.listener.single.PermissionListener
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay2
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MarkerFragment : Fragment() {
    private var _binding:FragmentMarkerBinding?=null
    private val binding get() =  _binding!!

    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var lat = 0.0
    private var long = 0.0

    companion object {
        fun instance() = MarkerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarkerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        reqPermission()
        setMap()
    }

    private fun setMap(){

        Configuration.getInstance().userAgentValue = "edsusantoo-maps"
        binding.map.setTileSource(TileSourceFactory.MAPNIK)


        val compassOverlay = CompassOverlay(requireContext(),InternalCompassOrientationProvider(context),binding.map)
        compassOverlay.enableCompass()
        binding.map.overlays.add(compassOverlay)

        val rotationGestureOverlay = RotationGestureOverlay(binding.map)
        rotationGestureOverlay.isEnabled
        binding.map.setMultiTouchControls(true)
        binding.map.overlays.add(rotationGestureOverlay)





    }

    @SuppressLint("SetTextI18n")
    private fun getLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location ->
            val mapController = binding.map.controller
            mapController.setZoom(16.1)

            val startPoint = GeoPoint(location.latitude,location.longitude)
            mapController.setCenter(startPoint)

            binding.tvLat.text = "Latitude : ${location.latitude}"
            binding.tvLong.text = "Longitude : ${location.longitude}"

            //marker
            val marker = Marker(binding.map)
            marker.position = startPoint
            marker.icon = ContextCompat.getDrawable(requireContext(),R.drawable.ic_round_emoji_people)
            marker.title = "Hellooww"
            marker.setAnchor(Marker.ANCHOR_CENTER,Marker.ANCHOR_CENTER)
            binding.map.overlays.add(marker)
            binding.map.invalidate()

        }

    }

    private fun reqPermission(){


        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener{
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                   getLocation()
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {

                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {

                }

            })
            .check()


    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val permissionsToRequest = ArrayList<String>();
        var i = 0;
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i]);
            i++;
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

}