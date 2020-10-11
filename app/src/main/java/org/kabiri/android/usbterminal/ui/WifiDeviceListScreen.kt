package org.kabiri.android.usbterminal.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kabiri.android.usbterminal.R
import org.kabiri.android.usbterminal.model.WifiDevice
import org.kabiri.android.usbterminal.viewmodel.DeviceState
import org.kabiri.android.usbterminal.viewmodel.WifiDeviceListViewModel

@ExperimentalCoroutinesApi
@Composable
fun WifiDeviceListScreen(
    viewModel: WifiDeviceListViewModel
) {
    viewModel.observeDevices()
    val wifiDevicesState = viewModel.wifiDevices.collectAsState(listOf())
    val deviceState by viewModel.deviceState.collectAsState()
    when(deviceState) {
        DeviceState.OFFLINE -> {
            NotClientMsg()
        }
        DeviceState.SERVER -> {
            NotClientMsg()
        }
        DeviceState.CLIENT -> {
            LazyColumnFor(items = wifiDevicesState.value) {
                DevicelItem(it)
            }
        }
    }
}

@Composable
fun DevicelItem(device: WifiDevice) {
    Text(text = device.simpleName)
}

@Composable
fun NotClientMsg() {
    Text(
        text = stringResource(id = R.string.settings_message_device_not_in_client_mode),
        color = colorResource(id = R.color.textColorSecondary)
    )
}