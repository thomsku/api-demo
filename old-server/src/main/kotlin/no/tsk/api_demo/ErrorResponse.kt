package no.tsk.api_demo

@JvmRecord
data class ErrorResponse(val message: String, val status: Int)