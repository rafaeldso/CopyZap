package br.com.rafael.copyzap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import br.com.rafael.copyzap.ui.component.CopyZapApp
import br.com.rafael.copyzap.ui.theme.CopyZapTheme
import br.com.rafael.copyzap.viewModel.ClipboardViewModel
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ClipboardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(listOf("ABCDEF012345")).build()
        )

        // Initialize the Google Mobile Ads SDK.
        MobileAds.initialize(this)

        setContent {
            CopyZapTheme {
                CopyZapApp(viewModel)
            }
        }

    }
}