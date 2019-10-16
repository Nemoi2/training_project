package bellintegrator.training.controller;


import bellintegrator.training.service.EmployeeService;
import bellintegrator.training.view.EmployeeListView;
import bellintegrator.training.view.EmployeeView;
import bellintegrator.training.view.EmployeesView;
import bellintegrator.training.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/user", produces = APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @JsonView(View.class)
    @ApiOperation(value = "Получить работника", httpMethod = "GET")
    @GetMapping("/{id}")
    public EmployeeView getEmployee(@PathVariable("id") final Long id) {
        return employeeService.getEmployee(id);
    }

    @ApiOperation(value = "Добавить нового работника", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void addEmployee(@RequestBody final EmployeesView employeesView) {
        employeeService.addEmployee(employeesView);
    }

    @ApiOperation(value = "Обновить данные работника", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/update")
    public void updateEmployee(@RequestBody final EmployeeView employeeView) {
        employeeService.updateEmployee(employeeView);
    }

    @JsonView(View.class)
    @ApiOperation(value = "Получить список работников", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/list")
    public List<EmployeeListView> employees(@RequestBody final EmployeeListView employeesView) {
        return employeeService.employees(employeesView);
    }
}