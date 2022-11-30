package spn.getlistkotlin.data

data class getListData(
    val dataObject: List<DataObject>,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)