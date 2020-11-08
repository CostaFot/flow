package com.feelsokman.androidtemplate.ui.fragments.combo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.work.WorkManager
import com.feelsokman.androidtemplate.databinding.FragmentComboBinding
import com.feelsokman.androidtemplate.ui.activity.viewmodel.TodoViewModel
import com.feelsokman.androidtemplate.ui.fragments.host.ViewBinder
import com.feelsokman.androidtemplate.utilities.viewmodel.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ComboFragment : Fragment() {

    @Inject internal lateinit var viewModelFactory: ViewModelFactory
    @Inject internal lateinit var workManager: WorkManager


    private val activityViewModel by activityViewModels<TodoViewModel>()

    private lateinit var viewBinder: ViewBinder

    private var _binding: FragmentComboBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentComboBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
