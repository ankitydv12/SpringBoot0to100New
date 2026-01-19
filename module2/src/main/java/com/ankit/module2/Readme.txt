Commit ---> Module2.3.3 -> Service Layer

/*
 * CONCEPT: Controller–DTO–Entity separation (Industry Standard)
 *
 * 1️⃣ Controller SHOULD deal only with DTOs, NOT Entities.
 *    - Controller is part of API layer.
 *    - Exposing Entity directly can leak database structure.
 *    - DTO controls what data goes in API request/response.
 *
 * 2️⃣ Repository layer SHOULD deal only with Entities.
 *    - Repository talks directly to the database.
 *    - JPA works only with Entity classes.
 *
 * 3️⃣ Service layer acts as a CONVERSION & BUSINESS layer.
 *    - Fetches Entity from Repository.
 *    - Converts Entity → DTO before sending to Controller.
 *    - Applies business logic if required.
 *
 * 4️⃣ Entity ↔ DTO conversion is done using ModelMapper.
 *    - ModelMapper is a third-party library.
 *    - It automatically maps fields with same names and types.
 *
 *
 * -------------------------
 * STEPS TO USE ModelMapper
 * -------------------------
 *
 * Step 1️⃣: Add ModelMapper dependency in pom.xml.
 *
 * Step 2️⃣: Create a config package.
 *         - Define a @Configuration class.
 *         - Create a @Bean of ModelMapper.
 *
 * Step 3️⃣: Inject ModelMapper into Service layer
 *         - Use constructor injection (BEST PRACTICE).
 *
 *
 * -------------------------
 * WORKING (CORE LOGIC)
 * -------------------------
 *
 * modelMapper.map(Entity_Object, DTO_Class.class)
 *
 * Meaning:
 * - Takes Entity object as input.
 * - Converts it into DTO object.
 * - Matches fields by name and type.
 *
 *
 * -------------------------
 * IMPORTANT INTERVIEW POINTS
 * -------------------------
 *
 * ✔ Never return Entity directly from Controller in real projects.
 * ✔ DTO improves security and API stability.
 * ✔ Service layer is the right place for mapping logic.
 * ✔ ModelMapper reduces boilerplate code (getters/setters).
 *
 */
