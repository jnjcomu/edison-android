package com.jnjcomu.edison.addition

import android.content.Context
import com.jnjcomu.edison.network.NetManager
import com.jnjcomu.edison.storage.AppSettingStorage
import com.jnjcomu.edison.storage.Configuration

/**
 * @author CodeRi13 <ruto1924@gmail.com>
 * @since 2017-07-10
 */

val Context.appStorage: AppSettingStorage
    get() = AppSettingStorage(this)

val Context.configurator: Configuration
    get() = Configuration(this)

val Context.netManager
    get() = NetManager(this)