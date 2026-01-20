package com.ankit.module2.controllers;
import com.ankit.module2.dto.EmployeeDTO;
import com.ankit.module2.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees") //this will work as main url inside it empId comes
public class EmployeeController {
    private final EmployeeService employeeServiceObj;

    //constructor --> injecting point
    public EmployeeController(EmployeeService employeeServiceObj) {
        this.employeeServiceObj = employeeServiceObj;
    }

    @GetMapping(path="/{empId}")
    public ResponseEntity<EmployeeDTO> sendDTO(@PathVariable(name = "empId") Long empID)
    {
       Optional<EmployeeDTO> employeeDTO =  employeeServiceObj.getEmployeeById(empID);
       return employeeDTO
               .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
               .orElse(ResponseEntity.notFound().build());
        /*
         * ============================================
         * sendDTO METHOD EXPLANATION (GET BY ID)
         * ============================================
         *
         * public ResponseEntity<EmployeeDTO> sendDTO(Long empId)
         *
         * Controller returns ResponseEntity<EmployeeDTO>,
         * NOT just EmployeeDTO.
         *
         * This allows:
         * - Returning 200 OK when employee exists
         * - Returning 404 NOT FOUND when employee does not exist
         */
        /*
         * Optional<EmployeeDTO> employeeDTO = employeeServiceObj.getEmployeeById(empID);
         *
         * Service returns Optional<EmployeeDTO>
         * → Represents "value may or may not exist"
         *
         * This avoids returning null
         * and forces explicit handling of absence.
         */
        /*
         * employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
         *
         * If value exists:
         * - Body  → employeeDTO
         * - Status → 200 OK
         *
         * This is clean, functional-style handling.
         */
        /*
         * .orElse(ResponseEntity.notFound().build())
         *
         * If value does NOT exist:
         * - No body
         * - Status → 404 NOT FOUND
         *
         * This is PERFECT REST behavior.
         *
         * INTERVIEW LINE:
         * "We use Optional + ResponseEntity to map domain absence
         *  to correct HTTP status."
         */


    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> allEmpList(@RequestParam(required = false,name = "inputid" , defaultValue = "123") Integer id , Integer age )
    {
        System.out.println(employeeServiceObj.getAllEmployee());
       List<EmployeeDTO> allEmpDto = employeeServiceObj.getAllEmployee();
       return ResponseEntity.ok(allEmpDto);

    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO inputemp){
        EmployeeDTO newDTO  =  employeeServiceObj.addEmployee(inputemp);
        return new ResponseEntity<>(newDTO, HttpStatus.CREATED);
        /*
         * ============================================
         * addEmployee METHOD EXPLANATION (POST)
         * ============================================
         *
         * POST is used to CREATE a new resource.
         *
         * In REST:
         * - Creating resource MUST return 201 CREATED
         * - Not 200 OK
         */
        /*
         * EmployeeDTO newDTO = employeeServiceObj.addEmployee(inputemp);
         *
         * Flow:
         * Controller → Service
         * Service:
         *   - DTO → Entity
         *   - save() to DB
         *   - Entity → DTO
         *
         * Controller NEVER touches Entity.
         */
        /*
         * return new ResponseEntity<>(newDTO, HttpStatus.CREATED);
         *
         * WHY "new" ResponseEntity ?
         *
         * Because:
         * - We want to manually specify the HTTP status
         * - ResponseEntity.ok() always returns 200 OK
         *
         * Here, 201 CREATED is the CORRECT semantic status.
         */
        /*
         * ================================
         * CONCEPT: HttpStatus
         * ================================
         *
         * HttpStatus is an ENUM representing HTTP status codes.
         *
         * Examples:
         * - HttpStatus.OK         → 200
         * - HttpStatus.CREATED    → 201
         * - HttpStatus.NOT_FOUND  → 404
         * - HttpStatus.BAD_REQUEST→ 400
         *
         * Using HttpStatus:
         * - Improves readability
         * - Avoids magic numbers
         * - Matches REST standards
         */
        /*
         * WHY HttpStatus.CREATED FOR POST
         *
         * REST RULE:
         * POST = resource creation
         *
         * Correct response:
         * - Status: 201 CREATED
         * - Body  : newly created resource
         *
         * This tells the client:
         * "Your request created something new on the server."
         */

    }

    //http://localhost:8080/employees/2
    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmpById(@RequestBody EmployeeDTO empDTO,@PathVariable(name = "employeeId")Long Id){
        EmployeeDTO updateDto=   employeeServiceObj.updateEmpById(empDTO,Id);
        return ResponseEntity.ok(updateDto);
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "employeeId") Long id)
    {
        boolean gotDeleted = employeeServiceObj.deleteEmployeeById(id);
        if(gotDeleted) return ResponseEntity.ok(gotDeleted);
        return ResponseEntity.notFound().build();
    }
    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                                 @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeServiceObj.updatePartialEmployeeById(employeeId, updates);
        if (employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
