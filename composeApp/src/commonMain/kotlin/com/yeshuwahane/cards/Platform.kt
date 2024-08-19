package com.yeshuwahane.cards

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform