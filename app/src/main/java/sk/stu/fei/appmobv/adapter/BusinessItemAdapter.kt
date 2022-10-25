package sk.stu.fei.appmobv.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import sk.stu.fei.appmobv.BusinessListFragmentDirections
import sk.stu.fei.appmobv.R
import sk.stu.fei.appmobv.model.Business

class BusinessItemAdapter(
    private val context: Context, private val businessList: List<Business>
) : RecyclerView.Adapter<BusinessItemAdapter.BusinessItemViewHolder>() {

    class BusinessItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val businessTitle: TextView = view.findViewById(R.id.business_title)
        val businessItem: MaterialCardView = view.findViewById(R.id.business_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.business_list_item, parent, false)
        return BusinessItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: BusinessItemViewHolder, position: Int) {
        val business: Business = businessList[position]
        holder.businessTitle.text = business.tags.businessTitle.toString()

        holder.businessItem.setOnClickListener {
            holder.view.findNavController().navigate(
                BusinessListFragmentDirections.actionBusinessListFragmentToBusinessInfoFragment(
                    businessId = business.id,
                    businessTitle = business.tags.businessTitle.toString(),
                    businessLat = business.lat.toString(),
                    businessLong = business.long.toString(),
                    businessOwner = business.tags.ownerName.toString(),
                    businessPhone = business.tags.phoneNumber.toString(),
                    businessWeb = business.tags.webUrl.toString()
                )
            )
        }

    }

    override fun getItemCount(): Int = businessList.size

}