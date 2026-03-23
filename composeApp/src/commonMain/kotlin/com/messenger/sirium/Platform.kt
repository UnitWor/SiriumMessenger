package com.messenger.sirium

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform