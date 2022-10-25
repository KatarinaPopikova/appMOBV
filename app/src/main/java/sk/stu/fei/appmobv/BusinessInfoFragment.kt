package sk.stu.fei.appmobv

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import sk.stu.fei.appmobv.databinding.FragmentBusinessInfoBinding

class BusinessInfoFragment : Fragment() {

    companion object {
        const val BUSINESS_ID = "business_id"
        const val BUSINESS_TITLE = "business_title"
        const val BUSINESS_LAT = "business_lat"
        const val BUSINESS_LONG = "business_long"
        const val BUSINESS_OWNER = "business_owner"
        const val BUSINESS_PHONE = "business_phone"
        const val BUSINESS_WEB = "business_web"
    }

    private var _binding: FragmentBusinessInfoBinding? = null
    private val binding get() = _binding!!

    private var businessId: Long = -1
    private lateinit var businessTitle: String
    private var businessLat: String? = null
    private var businessLong: String? = null
    private lateinit var businessOwner: String
    private lateinit var businessPhone: String
    private lateinit var businessWeb: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            businessId = it.getLong(BUSINESS_ID)
            businessTitle = it.getString(BUSINESS_TITLE).toString()
            businessLat = it.getString(BUSINESS_LAT).toString()
            businessLong= it.getString(BUSINESS_LONG).toString()
            businessOwner= it.getString(BUSINESS_OWNER).toString()
            businessPhone= it.getString(BUSINESS_PHONE).toString()
            businessWeb = it.getString(BUSINESS_WEB).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusinessInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.businessTitle.text = businessTitle
        binding.businessOwner.text = businessOwner
        binding.businessPhone.text = businessPhone


        binding.openWeb.setOnClickListener{
            if (businessWeb.startsWith("http://www.") || businessWeb.startsWith("https://www."))
            {
                val mapUrl: Uri = Uri.parse(businessWeb)
                val intent = Intent(Intent.ACTION_VIEW, mapUrl)
                context?.startActivity(intent)
            }
        }

        binding.openGpsButton.setOnClickListener{
            if (businessLat != null && businessLong != null){
            val mapUrl: Uri = Uri.parse("${BusinessFragment.GEO_PREFIX}${businessLat},${businessLong}")
            val intent = Intent(Intent.ACTION_VIEW, mapUrl)
            context?.startActivity(intent)}
        }

        binding.deleteButton.setOnClickListener {
            view.findNavController().navigate(
                BusinessInfoFragmentDirections.actionBusinessInfoFragmentToBusinessListFragment(
                    businessId = businessId
                )
            )
        }
    }
}