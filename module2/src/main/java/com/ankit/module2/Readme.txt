Commit ---> Module2.2.4 -> @RequestBody
This Module is sending the json from the postman


Error:400 :-
    * 400 Bad Request means Spring received the request but rejected it before hitting your controller method.
        this happen because Jackson fail to convert the json to JAVA object beacase it did not define the Default Constructor in EmployeDTO.java
        How Jackson work:
            Jackson needs to:
            Create an empty object
            Then set fields using setters
    Without a default constructor, Jackson cannot instantiate the object → 400 Bad Request













