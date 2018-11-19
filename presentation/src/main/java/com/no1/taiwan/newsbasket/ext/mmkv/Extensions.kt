package com.no1.taiwan.newsbasket.ext.mmkv

import com.tencent.mmkv.MMKV

val kvToken by lazy { MMKV.defaultMMKV(MMKV.SINGLE_PROCESS_MODE, "dG9rZW4=") }
