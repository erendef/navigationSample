package com.definex.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.definex.navigation.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()
        bundle.putString("stringValue","Definex")
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
        binding.buttonNotification.setOnClickListener {
            val context = requireContext().applicationContext
            val navController = findNavController()

            val pendingIntent = navController
                .createDeepLink()
                .setDestination(R.id.aFragment)
                .createPendingIntent()
            Notifier.postNotification(3,context, pendingIntent)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}