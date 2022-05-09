package com.mxy.justaconverter.jsons.cloudconvert.upload

data class CloudConvertUpload(
    val tasks: Tasks
)

data class Tasks(
    val convert_my_file: ConvertMyFile,
    val export_my_file: ExportMyFile,
    val import_my_file: ImportMyFile
)

data class ConvertMyFile(
    val input: String,
    val operation: String,
    val output_format: String
)

data class ExportMyFile(
    val input: String,
    val operation: String
)

data class ImportMyFile(
    val operation: String,
    val url: String
)