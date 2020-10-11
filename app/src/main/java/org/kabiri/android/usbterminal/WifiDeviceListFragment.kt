 package org.kabiri.android.usbterminal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kabiri.android.usbterminal.ui.WifiDeviceListScreen
import org.kabiri.android.usbterminal.viewmodel.WifiDeviceListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

 /**
 * Created by Ali Kabiri on 23.05.20.
 */
 @ExperimentalCoroutinesApi
 class WifiDeviceListFragment: Fragment() {

    private val viewModel: WifiDeviceListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // not the best way to use compose, but since we already created
        // the prefs screen before, we'll keep it this way for now!
        return inflater.inflate(R.layout.fragment_wifi_device_list, container, false)
            .apply { findViewById<ComposeView>(R.id.compose_view).setContent {
                WifiDeviceListScreen(
                    viewModel = viewModel
                )
            } }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleDeviceMode(requireContext()) // read the device mode and react to it!
    }
}