package com.no1.taiwan.newsbasket.ext.const

import java.math.BigInteger
import java.nio.ByteBuffer
import java.util.UUID

object UUID {
    private const val FOUR_BYTE = 16

    fun generateUniqueId(): Long {
        var random: Long

        do {
            val uid = UUID.randomUUID()
            val buffer = ByteBuffer.wrap(ByteArray(FOUR_BYTE)).apply {
                putLong(uid.leastSignificantBits)
                putLong(uid.mostSignificantBits)
            }
            val bi = BigInteger(buffer.array())

            random = bi.longValueExact()
        } while (random < 0)

        return random
    }
}
