package com.example.vechilemonitoringapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    private lateinit var etOwnerName: EditText
    private lateinit var etModelNumber: EditText
    private lateinit var etChassisNumber: EditText
    private lateinit var etVehicleType: EditText
    private lateinit var btnSubmit: Button
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etOwnerName = findViewById(R.id.etOwnerName)
        etModelNumber = findViewById(R.id.etModelNumber)
        etChassisNumber = findViewById(R.id.etChassisNumber)
        etVehicleType = findViewById(R.id.etVehicleType)
        btnSubmit = findViewById(R.id.btnSubmit)

        requestQueue = Volley.newRequestQueue(this)

        btnSubmit.setOnClickListener {
            submitForm()
        }
    }

    private fun submitForm() {
        val url = "https://vehiclebackend-zl30.onrender.com/vehicle/create"

        val ownerName = etOwnerName.text.toString()
        val modelNumber = etModelNumber.text.toString()
        val chassisNumber = etChassisNumber.text.toString()
        val vehicleType = etVehicleType.text.toString()

        val params = HashMap<String, String>()
        params["model"] = modelNumber
        params["chasisNo"] = chassisNumber
        params["owner"] = ownerName
        params["VehicleType"] = vehicleType

        val jsonObject = JSONObject(params as Map<*, *>)

        val request = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Log.d("RegisterActivity", "Response: $response")
                Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                Log.e("RegisterActivity", "Error: ${error.message}")
                if (error.networkResponse != null) {
                    Log.e("RegisterActivity", "Error response code: ${error.networkResponse.statusCode}")
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
}
