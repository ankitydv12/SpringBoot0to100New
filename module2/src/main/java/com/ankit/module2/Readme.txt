Commit ---> Module2.4.2 -> PutMapping | DeleteMapping | dbfunction

/*
 * ============================
 * PUT MAPPING (@PutMapping)
 * ============================
 *
 * @PutMapping is used to UPDATE an existing resource.
 *
 * - HTTP Method: PUT
 * - Id is usually passed as PathVariable.
 * - Updated data is passed in RequestBody (DTO).
 *
 * IMPORTANT:
 * - save() with existing ID performs UPDATE
 * - save() with null ID performs INSERT
 *
 * INTERVIEW POINT:
 * PUT should be idempotent (same request → same result).
 */


/*
 * ============================
 * DELETE MAPPING (@DeleteMapping)
 * ============================
 *
 * @DeleteMapping is used to DELETE a resource.
 *
 * - HTTP Method: DELETE
 * - Id is passed as PathVariable.
 *
 * FLOW:
 * Controller
 *   → receives employeeId
 *   → calls service.deleteEmployeeById(id)
 *
 * Service
 *   → checks if record exists
 *   → deletes record using repository
 *
 * IMPORTANT:
 * Always check existence before deletion to avoid exceptions.
 */


/*
 * =====================================================
 * SERVICE LAYER METHODS USED TO WORK WITH DATABASE
 * =====================================================
 *
 * Service layer:
 * - Contains business logic
 * - Converts DTO ↔ Entity
 * - Talks to Repository layer
 *
 * Controller NEVER talks directly to Repository.
 */


/*
 * findById(id)
 *
 * - Fetches a single record by primary key.
 * - Returns Optional<Entity>.
 *
 * Used when:
 * - Fetching one record (GET by id).
 *
 * In your code:
 * employeeRepository.findById(id).orElse(null)
 *
 * IMPORTANT:
 * Returning null is okay for learning,
 * but in production use custom exceptions.
 */


/*
 * findAll()
 *
 * - Fetches ALL records from database.
 * - Returns List<Entity>.
 *
 * Used when:
 * - Listing all employees.
 *
 * IMPORTANT:
 * In real projects, pagination should be used.
 */


/*
 * save(entity)
 *
 * - Inserts OR Updates record.
 *
 * CASE 1:
 * entity.id == null
 * → INSERT
 *
 * CASE 2:
 * entity.id != null
 * → UPDATE
 *
 * This is why save() is used in:
 * - POST (create)
 * - PUT (update)
 *
 * INTERVIEW GOLD:
 * save() is NOT only for insert.
 */


/*
 * existsById(id)
 *
 * - Checks whether a record exists.
 * - Returns boolean.
 *
 * Used before delete or update operations
 * to avoid errors.
 */


/*
 * deleteById(id)
 *
 * - Deletes record by primary key.
 *
 * IMPORTANT:
 * If record does not exist,
 * it may throw exception.
 *
 * That’s why existsById() is used before this.
 */


/*
 * =====================================================
 * BIG PICTURE (ARCHITECTURE TRUTH)
 * =====================================================
 *
 * Controller
 *   → Handles HTTP request/response
 *   → Works ONLY with DTO
 *
 * Service
 *   → Business logic
 *   → DTO ↔ Entity conversion
 *
 * Repository
 *   → Database interaction
 *   → Works ONLY with Entity
 *
 * This separation is INDUSTRY STANDARD.
 */

