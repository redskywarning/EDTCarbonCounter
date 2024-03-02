package com.example.edtcarboncounter.data

class projectObject(var projectName: String, var materials: MutableList<materialObject>, var recyclableMats: List<materialObject>)

var project = projectObject(projectName = "", materials = mutableListOf(), recyclableMats = mutableListOf())

var projects = listOf<projectObject>()

