package com.hp.java.core;

import java.util.Comparator;
import java.util.TreeSet;

public class EmployeeSortTest {

	public static void main(String[] args) {
		TreeSet<Employee> empList = new TreeSet<Employee>(
					new Comparator<Employee>() {

						@Override
						public int compare(Employee emp1, Employee emp2) {
							return emp1.compareTo(emp2);
						}
					}
				);
		
		Employee emp1 = new Employee(100, 0, 10, 100000);
		Employee emp2 = new Employee(200, 1, 20, 200000);
		Employee emp3 = new Employee(300, 0, 10, 300000);
		Employee emp4 = new Employee(400, 2, 20, 200000);
		
		empList.add(emp1);
		empList.add(emp4);
		empList.add(emp2);
		empList.add(emp3);
		
		for(Employee emp:empList){
			System.out.println("Employee id="+emp.getEmpId()+" designation="+emp.getDesignation()+
					" age="+emp.getAge()+" Salary="+emp.getSalary());
		}
	}

}