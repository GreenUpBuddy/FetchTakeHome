package android.jds1223.fetchtakehome
data class DataModel(
    val id: String,
    val name: String,
    val listId: Int
) {
override fun toString(): String {
    return "{id=$id, name='$name', listId=$listId}\n"
}}