package com.feelsokman.androidtemplate.ui.fragments.host

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.work.WorkManager
import com.feelsokman.androidtemplate.databinding.FragmentHostBinding
import com.feelsokman.androidtemplate.di.component.AppComponent
import com.feelsokman.androidtemplate.di.getComponent
import com.feelsokman.androidtemplate.ui.activity.viewmodel.MainViewModel
import com.feelsokman.androidtemplate.ui.base.BaseFragment
import com.feelsokman.androidtemplate.ui.fragments.host.viewmodel.HostViewModel
import com.feelsokman.androidtemplate.utilities.viewmodel.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HostFragment : BaseFragment(), ViewBinder.Callback {
    override fun onButtonClicked() {
        //
    }

    @Inject internal lateinit var viewModelFactory: ViewModelFactory
    @Inject internal lateinit var workManager: WorkManager
    private val viewModel by viewModels<HostViewModel> { viewModelFactory }
    private val activityViewModel by activityViewModels<MainViewModel>()

    private lateinit var viewBinder: ViewBinder

    private var _binding: FragmentHostBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        injectDependencies(context)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            activityViewModel.textStateFlow.collect { todoString ->
                Toast.makeText(view.context, todoString ?: "null", Toast.LENGTH_LONG).show()
            }
        }

        binding.button.setOnClickListener {
            viewModel.getTodo()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun injectDependencies(context: Context) {
        (context as AppCompatActivity).application.getComponent<AppComponent>().inject(this)
    }
}
