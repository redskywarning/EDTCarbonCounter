package com.example.edtcarboncounter.data

class projectObject(var projectName: String, var materials: List<materialObject>)

var project = projectObject(projectName = "", materials = listOf())

var projects = listOf<projectObject>()

