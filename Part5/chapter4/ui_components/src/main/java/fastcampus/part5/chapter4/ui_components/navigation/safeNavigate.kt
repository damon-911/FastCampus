package fastcampus.part5.chapter4.ui_components.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest

fun NavController.safeNavigate(url: String) {
    currentDestination?.run {
        navigate(
            NavDeepLinkRequest.Builder
                .fromUri(url.toUri())
                .build()
        )
    }
}