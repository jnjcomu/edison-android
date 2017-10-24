package com.jnjcomu.edison.addition

import android.content.Context
import com.jnjcomu.edison.network.NetManager
import com.jnjcomu.edison.storage.AppSettingStorage

/**
 * @author kimwoojae <wj1187@naver.com>
 * @since 2017-10-23
 */

val Context.netManager
    get() = NetManager(this)

val Context.appStorage: AppSettingStorage
    get() = AppSettingStorage(this)