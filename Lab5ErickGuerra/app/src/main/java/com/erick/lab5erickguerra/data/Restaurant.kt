package com.erick.lab5erickguerra.data

import java.io.Serializable

data class Restaurant (
    val name: String,
    val address: String,
    val schedule: String
) : Serializable