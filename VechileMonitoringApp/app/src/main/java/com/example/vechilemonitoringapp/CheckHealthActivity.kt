package com.example.vechilemonitoringapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class CheckHealthActivity : AppCompatActivity() {

    private lateinit var etOwnerName: EditText
    private lateinit var etModelNumber: EditText
    private lateinit var btnSubmit: Button
    private lateinit var listView: ListView
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_health)

        etOwnerName = findViewById(R.id.etOwnerName)
        etModelNumber = findViewById(R.id.etModelNumber)
        btnSubmit = findViewById(R.id.btnSubmit)
        listView = findViewById(R.id.listView)
        requestQueue = Volley.newRequestQueue(this)

        btnSubmit.setOnClickListener {
            fetchVehicleDetails()
        }
    }

    private fun fetchVehicleDetails() {
        val url = "https://vehiclebackend-zl30.onrender.com/vehicle/getDetail"

        val ownerName = etOwnerName.text.toString()
        val modelNumber = etModelNumber.text.toString()

        if (ownerName.isEmpty() || modelNumber.isEmpty()) {
            Toast.makeText(this, "Please enter both owner name and model number", Toast.LENGTH_LONG).show()
            return
        }

        val params = HashMap<String, String>()
        params["owner"] = ownerName
        params["chasisNo"] = modelNumber

        val jsonObject = JSONObject(params as Map<*, *>)

        val request = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Log.d("CheckHealthActivity", "Response: $response")
                val vehicleDetailsList = parseVehicleDetails(response)
                populateListView(vehicleDetailsList)
            },
            Response.ErrorListener { error ->
                Log.e("CheckHealthActivity", "Error: ${error.message}")
                if (error.networkResponse != null) {
                    Log.e("CheckHealthActivity", "Error response code: ${error.networkResponse.statusCode}")
                }
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }) {
            override fun getBody(): ByteArray {
                return jsonObject.toString().toByteArray(Charsets.UTF_8)
            }

            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }

        requestQueue.add(request)
    }

    private fun parseVehicleDetails(response: String): List<VehicleDetails> {
        val vehicleDetailsList = mutableListOf<VehicleDetails>()
        val jsonObject = JSONObject(response)
        val vehicleDetail = jsonObject.getJSONObject("vehicleDetail")

        val model = vehicleDetail.getString("model")
        val chasisNo = vehicleDetail.getString("chasisNo")
        val vehicleType = vehicleDetail.getString("VehicleType")
        val vehicleStatus = vehicleDetail.getString("vehicleStatus")
        val owner = vehicleDetail.getString("owner")
        val pollution = vehicleDetail.getString("pollution")
        val insurance = vehicleDetail.getString("insurance")

        val vehicleDetails = VehicleDetails(model, chasisNo, vehicleType, vehicleStatus, owner, pollution, insurance)
        vehicleDetailsList.add(vehicleDetails)

        return vehicleDetailsList
    }

    private fun populateListView(vehicleDetailsList: List<VehicleDetails>) {
        val adapter = VehicleDetailsAdapter(this, vehicleDetailsList)
        listView.adapter = adapter
    }
}
