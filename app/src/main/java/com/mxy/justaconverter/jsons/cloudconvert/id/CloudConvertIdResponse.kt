package com.mxy.justaconverter.jsons.cloudconvert.id

data class CloudConvertIdResponse(
    val `data`: Data
)

data class Data(
    val created_at: String,
    val ended_at: Any,
    val id: String,
    val links: Links,
    val started_at: Any,
    val status: String,
    val tag: Any,
    val tasks: List<Task>
)

data class Links(
    val self: String
)

data class Task(
    val code: Any,
    val copy_of_task_id: Any,
    val created_at: String,
    val credits: Any,
    val depends_on_task_ids: List<String>,
    val ended_at: Any,
    val engine: Any,
    val engine_version: Any,
    val host_name: Any,
    val id: String,
    val job_id: String,
    val links: LinksX,
    val message: Any,
    val name: String,
    val operation: String,
    val percent: Int,
    val priority: Int,
    val result: Any,
    val retry_of_task_id: Any,
    val started_at: Any,
    val status: String,
    val storage: String,
    val user_id: Int
)

data class LinksX(
    val self: String
)