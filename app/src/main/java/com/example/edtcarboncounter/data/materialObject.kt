package com.example.edtcarboncounter.data

//Recyclable = 1
//Non Recyclable = 0

// Deleted = 1
//Not Deleted = 0


class materialObject (var material: String, var Smkg: String, var Lmkg: Long, var transports: MutableList<transportObject>, var recyclable: Int, var deleted: Int)

