package com.example.KotlinProgramming

class Student(val status: StudentStatus) {
}

//enum class StudentStatus {
//    NOT_ENROLLED,
//    ACTIVE,
//    GRADUATED;
//    var courseId: String? = null // Used for ACTIVE only
//}

//A better solution would be to use a sealed class to model the student statuses.
sealed class StudentStatus {
    object NotEnrolled : StudentStatus()
    class Active(val courseId: String) : StudentStatus()
    object Graduated : StudentStatus()
}

fun main(args: Array<String>) {
    val student = Student(StudentStatus.Active("Kotlin101"))
    studentMessage(student.status)
}
fun studentMessage(status: StudentStatus): String {
    return when (status) {
        is StudentStatus.NotEnrolled -> "Please choose a course!"
        is StudentStatus.Active -> "You are enrolled in: ${status.courseId}"
        is StudentStatus.Graduated -> "Congratulations!"
    } }