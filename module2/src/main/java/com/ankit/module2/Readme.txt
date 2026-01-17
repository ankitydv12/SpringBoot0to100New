Commit ---> Module2.3.1 -> Enitity | Repository

📌 Concept of Repository Layer in Spring Boot

In Spring Boot, the Repository layer acts as the direct communication bridge between the application and the database.
The controller does not talk to the database directly; instead, it delegates all persistence-related operations to the repository. By extending JpaRepository,
the repository automatically gets ready-made CRUD operations such as save(), findById(), and findAll() without writing any SQL. Spring Data JPA generates the actual implementation at runtime using Hibernate,
which means the developer only defines what data is needed, not how it is fetched. This abstraction keeps the code clean, maintainable, and aligned with industry standards.

📌 How Database Working Happens Behind the Scenes

When a controller method calls a repository method like findById() or save(), Spring Boot forwards the request to Hibernate (the JPA implementation).
Hibernate translates the Java entity (EmployeeEntity) into SQL queries based on the entity annotations such as @Entity, @Table, and @Id. These SQL queries are then executed against the configured database using the DataSource. The database returns the result,
 which Hibernate converts back into Java objects and returns to the repository, which finally sends the data to the controller. Throughout this process, the developer never writes SQL explicitly, yet full database interaction happens efficiently and safely.

/*
 * IMPORTANT ENTITY POINTS (INTERVIEW GOLD):
 *
 * 1. Entity must have:
 *    - @Entity
 *    - @Id
 *    - Default constructor
 *
 * 2. Entity objects are MANAGED by Hibernate.
 *
 * 3. Entity should NOT contain business logic.
 *
 * 4. Entity is NOT DTO.
 *    - Entity → DB mapping
 *    - DTO → API response mapping
 */


/*
 * JpaRepository<T, ID>
 *
 * T  → Entity class
 * ID → Primary key type
 *
 * INTERVIEW POINTS:
 *
 * 1. JpaRepository extends:
 *    - PagingAndSortingRepository
 *    - CrudRepository
 *
 * 2. Provides pagination & sorting support.
 *
 * 3. Uses Hibernate internally.
 *
 * 4. Repository layer SHOULD NOT contain business logic.
 */
