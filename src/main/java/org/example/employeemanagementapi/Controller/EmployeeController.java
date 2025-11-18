package org.example.employeemanagementapi.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping
    public String list(){
        return "Employees list";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return "Deleted employee " + id;
    }
}
