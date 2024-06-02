package com.example.vechilemonitoringapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class VehicleDetailsAdapter(private val context: Context, private val vehicleDetailsList: List<VehicleDetails>) : BaseAdapter() {

    override fun getCount(): Int {
        return vehicleDetailsList.size
    }

    override fun getItem(position: Int): Any {
        return vehicleDetailsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_vehicle_details, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val vehicleDetails = vehicleDetailsList[position]
        holder.tvModel.text = "Model: ${vehicleDetails.model}"

        holder.tvOwner.text = "Owner: ${vehicleDetails.owner}"
        holder.tvChasisNo.text = "Chasis No: ${vehicleDetails.chasisNo}"
        holder.tvVehicleType.text = "Vehicle Type: ${vehicleDetails.vehicleType}"
        holder.tvVehicleStatus.text = "Vehicle Status: ${vehicleDetails.vehicleStatus}"
        holder.tvPollution.text = "Pollution: ${vehicleDetails.pollution}"
        holder.tvInsurance.text = "Insurance: ${vehicleDetails.insurance}"

        return view
    }

    private class ViewHolder(view: View) {
        val tvModel: TextView = view.findViewById(R.id.tvModel)
        val tvChasisNo: TextView = view.findViewById(R.id.tvChasisNo)
        val tvVehicleType: TextView = view.findViewById(R.id.tvVehicleType)
        val tvVehicleStatus: TextView = view.findViewById(R.id.tvVehicleStatus)
        val tvOwner: TextView = view.findViewById(R.id.tvOwner)
        val tvPollution: TextView = view.findViewById(R.id.tvPollution)
        val tvInsurance: TextView = view.findViewById(R.id.tvInsurance)
    }
}
