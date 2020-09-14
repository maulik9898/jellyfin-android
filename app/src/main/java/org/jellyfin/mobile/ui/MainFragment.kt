package org.jellyfin.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.backpress.LocalBackPressHandler
import com.github.zsoltk.compose.savedinstancestate.BundleScope
import com.github.zsoltk.compose.savedinstancestate.saveLocal
import org.jellyfin.mobile.databinding.FragmentMainBinding
import org.jellyfin.mobile.ui.utils.ContextTheme
import org.jellyfin.mobile.utils.applyWindowInsetsAsMargins

class MainFragment : Fragment() {
    private val backPressHandler = BackPressHandler()

    private var _viewBinding: FragmentMainBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val composeView: ComposeView get() = viewBinding.composeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (!backPressHandler.handle()) {
                isEnabled = false
                activity?.onBackPressed()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _viewBinding = FragmentMainBinding.inflate(inflater, container, false)
        return composeView.apply { applyWindowInsetsAsMargins() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewTreeLifecycleOwner.set(composeView, this)

        // Apply window insets
        ViewCompat.requestApplyInsets(composeView)

        composeView.setContent {
            BundleScope(savedInstanceState) {
                CompositionLocalProvider(LocalBackPressHandler provides backPressHandler) {
                    composeView.context.ContextTheme {
                        AppContent()
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.saveLocal()
    }
}
