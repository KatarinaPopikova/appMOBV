package sk.stu.fei.appmobv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import sk.stu.fei.appmobv.databinding.FragmentBusinessFormBinding

/**
 * A simple [Fragment] subclass.
 * Use the [BusinessFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessFormFragment : Fragment() {

    private var _binding: FragmentBusinessFormBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusinessFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.confirmButton.setOnClickListener {
            val action =
                BusinessFormFragmentDirections.actionBusinessFormFragmentToBusinessFragment(
                    nameInput = binding.nameInput.toString(),
                    businessNameInput = binding.businessNameInput.toString(),
                    businessLatitudeInput = binding.businessLatitudeInput.toString(),
                    businessAltitudeInput = binding.businessAltitudeInput.toString()
                )

            view.findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

