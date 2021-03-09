package com.covidapp.model

/**
 * @author emre.memis@ovidos.com
 */

data class Covid(
    var infected: Int?,
    var deceased: Int?,
    var recovered: Int?,
    var tested: Int?,
    var critical: Int?,
    var dailyTested: Int?,
    var dailyInfected: Int?,
    var dailyDeceased: Int?,
    var dailyRecovered: Int?
)