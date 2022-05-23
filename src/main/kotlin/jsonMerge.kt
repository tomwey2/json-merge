import kotlinx.serialization.json.*

/**
 * JSON Merge
 *
 * Utility function that merges two JSONs together.
 * Here are the merging rules:
 * 1. If a field exists only in one JSON, add it to the result.
 * 2. If a field exists in both JSONs and is a JSON object in both cases, merge these objects.
 * 3. If a field exists in both JSONs and at least one of them is not a JSON object, use the value from the second JSON.
 */

fun jsonMerge(a: String, b: String): String {
    val jsonA = Json.parseToJsonElement(a).jsonObject
    val jsonB = Json.parseToJsonElement(b).jsonObject

    val jsonMerge = buildJsonObject {
        for (key in jsonA.keys) {
            if (!jsonB.containsKey(key)) {
                // first if condition of the task description
                put(key, jsonA.getValue(key))
                jsonB.forEach { item -> put(item.key, item.value) }
            } else {
                if (jsonA.getValue(key) is JsonObject && jsonB.getValue(key) is JsonObject) {
                    // second if condition of the task description
                    val merge = jsonMerge(
                        jsonA.getValue(key).jsonObject.toString(),
                        jsonB.getValue(key).jsonObject.toString()
                    )
                    put(key, Json.parseToJsonElement(merge))
                } else {
                    // third if condition of the task description
                    put(key, jsonB.getValue(key))
                }
            }
        }

    }

    return jsonMerge.toString()
}

