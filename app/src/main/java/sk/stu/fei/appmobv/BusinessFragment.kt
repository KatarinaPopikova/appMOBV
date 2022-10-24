package sk.stu.fei.appmobv

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import sk.stu.fei.appmobv.databinding.FragmentBusinessBinding

/**
 * A simple [Fragment] subclass.
 * Use the [BusinessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessFragment : Fragment() {

    companion object {
        const val NAME_INPUT = "name_input"
        const val BUSINESS_NAME_INPUT = "business_name_input"
        const val BUSINESS_LATITUDE_INPUT = "business_latitude_input"
        const val BUSINESS_ALTITUDE_INPUT = "business_altitude_input"


        const val GEO_PREFIX = "geo:"

    }

    private var _binding: FragmentBusinessBinding? = null
    private val binding get() = _binding!!

    private lateinit var drinkLottie: LottieAnimationView


    private lateinit var nameInput: String
    private lateinit var businessNameInput: String
    private lateinit var businessLatitudeInput: String
    private lateinit var businessAltitudeInput: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nameInput = it.getString(NAME_INPUT).toString()
            businessNameInput = it.getString(BUSINESS_NAME_INPUT).toString()
            businessLatitudeInput = it.getString(BUSINESS_LATITUDE_INPUT).toString()
            businessAltitudeInput = it.getString(BUSINESS_ALTITUDE_INPUT).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBusinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.name.text = nameInput
        binding.businessTitle.text = businessNameInput
        drinkLottie = binding.wineAnimation
        binding.startAnimationButton.setOnClickListener {
            drinkLottie.playAnimation()

        }

        binding.openGpsButton.setOnClickListener{
            val mapUrl: Uri = Uri.parse("${GEO_PREFIX}${businessLatitudeInput},${businessAltitudeInput}")
            val intent = Intent(Intent.ACTION_VIEW, mapUrl)
            context?.startActivity(intent)
        }

    }
}