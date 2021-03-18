package com.covidapp.model

/**
 * @author emre.memis@ovidos.com
 */

data class Covid(
    var gunluk_test: String?,
    var gunluk_vaka: String?,
    var gunluk_hasta: String?,
    var gunluk_vefat: String?,
    var gunluk_iyilesen: String?,
    var toplam_test: String?,
    var toplam_hasta: String?,
    var toplam_vefat: String?,
    var toplam_iyilesen: String?,
    var agir_hasta_sayisi: String?,
    var haberler: List<News>?
)