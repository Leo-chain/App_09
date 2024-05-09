package com.example.app_09

class Bprecords (
    val bprecords: List<bprecord>
)

data class bprecord(
    val datetime: String,
    val sys: Int,
    val dia: Int,
    val hr: Int
)