package com.mxy.justaconverter.jsons.cloudconvert.download

data class CloudConvertDownloadResponse(
    val `data`: Data
)

data class Data(
    val id: String,
    val status: String,
    val tasks: List<Task>
)

data class Task(
    val id: String,
    val name: String,
    val operation: String,
    val result: Result,
    val status: String
)

data class Result(
    val files: List<File>
)

data class File(
    val filename: String,
    val url: String
)