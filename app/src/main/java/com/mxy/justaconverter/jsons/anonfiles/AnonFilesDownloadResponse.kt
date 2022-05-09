package com.mxy.justaconverter.jsons.anonfiles

data class AnonFilesDownloadResponse(
    val `data`: Data,
    val status: Boolean
)

data class Data(
    val `file`: File
)

data class File(
    val metadata: Metadata,
    val url: Url
)

data class Metadata(
    val id: String,
    val name: String,
    val size: Size
)

data class Url(
    val full: String,
    val short: String
)

data class Size(
    val bytes: Int,
    val readable: String
)