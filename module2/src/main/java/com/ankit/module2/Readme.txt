Commit --->2.4.3 -> Patch|Reflextion use in patch|@JsonProperty("isActive")
/*
 * ============================
 * PUT (Full Update)
 * ============================
 *
 * - Replaces the ENTIRE resource.
 * - Client must send ALL fields.
 * - Missing fields may become NULL.
 *
 * Example:
 * Existing Employee:
 * { id: 1, name: "Ankit", age: 25, email: "a@x.com" }
 *
 * PUT Request:
 * { name: "Rahul" }
 *
 * Result:
 * name  → updated
 * age   → NULL ❌
 * email → NULL ❌
 *
 * PUT assumes client knows full resource state.
 */


/*
 * ============================
 * PATCH (Partial Update)
 * ============================
 *
 * - Updates ONLY provided fields.
 * - Other fields remain unchanged.
 *
 * PATCH Request:
 * { name: "Rahul" }
 *
 * Result:
 * name  → updated
 * age   → unchanged
 * email → unchanged
 *
 * PATCH is safer and more efficient.
 */