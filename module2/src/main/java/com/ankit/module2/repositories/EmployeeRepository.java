package com.ankit.module2.repositories;

import com.ankit.module2.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
   /*
 * Repository layer communicates with the DATABASE.
 *
 * Controller → Service → Repository → Database
 *
 * Repository hides SQL complexity from developer.
 */

/*
 * @Repository tells Spring:
 * "This interface is a DAO component"
 *
 * Also enables exception translation.
 */

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    /*
     * JpaRepository provides READY-MADE CRUD methods:
     *
     * save()
     * findById()
     * findAll()
     * deleteById()
     * count()
     * existsById()
     *
     * NO implementation required.
     * Spring Data JPA creates proxy at runtime.
     */

}
