Commit ---> Module2.2.1 ---> @Controller | @RestController | @GetMapping | @RequestParam
This Module is About Presentation Layer

@Controller:-
The @Controller annotation in Spring is used to define a controller class.
A controller in Spring is responsible for handling incoming web requests, processing them, and returning a response, typically in the form of a view (like a JSP page).
@Controller is inside the @RestController

@RestController:-
The @RestController annotation is a specialized version of @Controller.
It combines @Controller and @ResponseBody, meaning that instead of returning a view, it returns the response body directly, typically in JSON or XML format.
if @RestController define a controller class we dont have to use @ResponseBody inside the class but it already contain it
@ResponseBody convert the java object to xml , json

The @RestController annotation is a shorthand for @Controller and
@ResponseBody, meaning all methods in the controller will return
JSON/XML directly to the response body.

jackson convert the java object to the Java Object

You can use the @RequestMapping annotation to map requests to
controllers methods. It has various attributes to match by URL, HTTP
method, request parameters, headers, and media types.
There are also HTTP method specific shortcut variants of
@RequestMapping:
• @GetMapping
• @PostMapping
• @PutMapping
• @DeleteMapping
• @PatchMapping













