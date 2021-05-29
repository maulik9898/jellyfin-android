package org.jellyfin.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewTreeLifecycleOwner
import org.jellyfin.mobile.databinding.FragmentMainBinding
import org.jellyfin.mobile.ui.utils.ContextTheme
import org.jellyfin.mobile.utils.applyWindowInsetsAsMargins

class MainFragment : Fragment() {
    private var _viewBinding: FragmentMainBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val composeView: ComposeView get() = viewBinding.composeView

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
            composeView.context.ContextTheme {
                AppContent()
            }
        }
    }
}
