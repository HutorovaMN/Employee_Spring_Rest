package com.spring.rest.controller;

import com.spring.rest.Entity.Employee;
import com.spring.rest.Service.EmployeeService;
import com.spring.rest.exception_handling.EmployeeIncorrectData;
import com.spring.rest.exception_handling.NoSuchEmployeeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // контроллер, управлеющий рест запросами и ответами
@RequestMapping("/api")
public class MyRESTController {

	private final EmployeeService employeeService;

	public MyRESTController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public List<Employee> showAllEmployees() {

		return employeeService.getAllEmployees();
	}

	@GetMapping("/employees/{id}") // получаем id из самого url адреса
	public Employee getEmployee(@PathVariable int id) {
		Employee employee = employeeService.getEmployee(id);

		if (employee == null) {
			throw new NoSuchEmployeeException("There is no  employee with ID = " + id + " in DataBase");
		}

		return employee;
	}

	// Контроллер для отвловки NuSuchEmployeeException
	// ResponseEntity - обертка над http response
	// <T> = <EmployeeIncorrectData> - тип объекта, который добавляться в http
	// response body при выбросе указанного
	// Exception
	@ExceptionHandler
	public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception) {
		EmployeeIncorrectData data = new EmployeeIncorrectData();
		data.setInfo(exception.getMessage());

		return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
	}

	// Отловка вообще всех исключений
	@ExceptionHandler
	public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception) {
		EmployeeIncorrectData data = new EmployeeIncorrectData();
		data.setInfo(exception.getMessage());

		return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
	}

	// @PostMapping - откловка POST запроса
	// @RequestBody - параметр из "тела запроса"
	@PostMapping("/employees")
	public Employee addNewEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);

		return employee;
	}

	// @PutMapping - откловка PUT запроса
	// @RequestBody - параметр из "тела запроса"
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);

		return employee;
	}

	// @DeleteMapping - откловка DELETE запроса
	// @RequestBody - параметр из "тела запроса"
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id) {
		Employee employee = employeeService.getEmployee(id);

		if (employee == null) {
			throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in DataBase");
		}

		employeeService.deleteEmployee(id);
		return "Employee with ID = " + id + " was deleted";
	}
}