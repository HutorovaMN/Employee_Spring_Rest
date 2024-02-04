package com.spring.rest.Service;

import com.spring.rest.Entity.Employee;
import java.util.List;

public interface EmployeeService {
	public List<Employee> getAllEmployees();

	public void saveEmployee(Employee employee);

	public Employee getEmployee(int id);

	public void deleteEmployee(int id);
}