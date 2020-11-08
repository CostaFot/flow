package com.feelsokman.androidtemplate.ui.fragments.another

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.di.component.AppComponent
import com.feelsokman.androidtemplate.di.getComponent
import com.feelsokman.androidtemplate.extensions.savedState
import com.feelsokman.androidtemplate.preferences.AppPreferences
import com.feelsokman.androidtemplate.ui.activity.viewmodel.TodoViewModel
import com.feelsokman.androidtemplate.ui.base.BaseFragment
import com.feelsokman.androidtemplate.ui.fragments.another.viewmodel.AnotherViewModel
import com.feelsokman.androidtemplate.utilities.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_another.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnotherFragment : BaseFragment() {

    @Inject internal lateinit var appPreferences: AppPreferences
    @Inject internal lateinit var viewModelFactory: ViewModelFactory

    private val viewModelAnother by viewModels<AnotherViewModel> { viewModelFactory }
    private val todoViewModel by activityViewModels<TodoViewModel>()

    private val state by savedState()
    private var count: Int by state.property(defaultValue = 0)
    private var text: String by state.property(
        { getString(it, "default value") },
        { key, value -> putString(key, value) })
    private var bool: Boolean by state.property(defaultValue = false)

    override fun onAttach(context: Context) {
        injectDependencies(context)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_another, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            todoViewModel.todoSharedFlow.collect { todoString ->
                if (todoString != null) {
                    textView1.text = todoString
                } else {
                    textView1.text = "null"
                }
            }
        }
    }

    override fun injectDependencies(context: Context) {
        (context as AppCompatActivity).application.getComponent<AppComponent>().inject(this)
    }
}
