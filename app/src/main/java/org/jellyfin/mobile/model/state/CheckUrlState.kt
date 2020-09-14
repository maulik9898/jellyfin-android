package org.jellyfin.mobile.model.state

import androidx.annotation.StringRes
import org.jellyfin.mobile.R

sealed class CheckUrlState {
    object Unchecked : CheckUrlState()
    object Pending : CheckUrlState()
    object Success : CheckUrlState()
    class Error(@StringRes val message: Int = R.string.connection_error_cannot_connect) : CheckUrlState()
}
