package com.example.alpha10

data class Percurso(var title: String? = null, var description: String? = null, var dificulty: String? = null,
                    var distance: Integer? = null, var topics: ArrayList<String>? = null, var GPSCoords: ArrayList<Integer>? = null,
                    var img: ArrayList<String>? = null) {
}