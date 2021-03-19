package com.covidapp.model

import java.io.Serializable

/**
 * @author emre.memis@ovidos.com
 */

data class News(
    var url: String?,
    var description: String?,
    var image: String?,
    var name: String?,
    var source: String?
) : Serializable