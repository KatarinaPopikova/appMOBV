package sk.stu.fei.appmobv

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import sk.stu.fei.appmobv.adapter.BusinessItemAdapter
import sk.stu.fei.appmobv.databinding.FragmentBusinessListBinding

class BusinessListFragment : Fragment() {

    companion object {
        const val BUSINESS_ID = "business_id"

    }

    private var _binding: FragmentBusinessListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView


    private var sortMode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { it1 ->
            {
                MainActivity.businessList.removeAll { it2 -> it2.id == it1.getLong(BUSINESS_ID) }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusinessListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.business_list_menu, menu)

                val sortButton = menu.findItem(R.id.sort_business_list_button)
                sortButton.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_sort)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.sort_business_list_button -> {
                        sortMode = when (sortMode) {
                            "asc" -> "desc"
                            "desc" -> null
                            else -> "asc"
                        }
                        sortBusinessList()
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        recyclerView = binding.businessListView
        sortBusinessList()

        binding.addBusiness.setOnClickListener {
            view.findNavController()
                .navigate(BusinessListFragmentDirections.actionBusinessListFragmentToBusinessFormFragment())
        }
    }

    private fun sortBusinessList() {
        when (sortMode) {
            "asc" -> recyclerView.adapter = BusinessItemAdapter(
                requireContext(),
                MainActivity.businessList.sortedBy { it.tags.businessTitle })
            "desc" -> recyclerView.adapter = BusinessItemAdapter(
                requireContext(),
                MainActivity.businessList.sortedByDescending { it.tags.businessTitle })
            else -> recyclerView.adapter =
                BusinessItemAdapter(requireContext(), MainActivity.businessList)
        }

    }

}