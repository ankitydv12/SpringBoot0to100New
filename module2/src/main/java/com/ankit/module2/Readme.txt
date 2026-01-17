Commit ---> Module2.3.3 -> Service Layer

    Service layer is implemented and now it handle all the bussiness logic
    Controller have the bean of the Service layer which call the function of the EmployeeService
    The Service Layer or EmployeeService have the Repositotry layer which handle db  query and connect to database
    The Problem in this code:
        We have to return EmployeeDTO from service layer but we are returing Employee Entity .
        We need to convert into the EmployeeDTO with help of library called modelmapper. In next commit


Service Layer:
    •The service layer acts as a bridge between the persistence layer
    (responsible for data access) and the presentation layer (handling
    user interaction)
    • It encapsulates the business logic of the application, orchestrates
    interactions between different components, and provides a clean
    interface for external clients to interact with the system.
    • By abstracting away the complexities of data access and business
    operations, the service layer promotes modularity, maintainability,
    and scalability.