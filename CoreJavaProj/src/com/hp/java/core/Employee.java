package com.hp.java.core;

public class Employee implements Comparable<Employee>{
	
	private int empId;
	private int designation;
	private int age;
	private int salary;
	
	public Employee(int empId,int desg,int age,int salary) {
		this.empId = empId;
		this.designation = desg;
		this.age = age;
		this.salary = salary;
	}
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getDesignation() {
		return designation;
	}

	public void setDesignation(int designation) {
		this.designation = designation;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public int compareTo(Employee otherEmp) {
		if(this.designation != otherEmp.designation){
			return  Integer.compare(this.designation, otherEmp.designation);
		}
		else if(this.age != otherEmp.age){
			return Integer.compare(this.age, otherEmp.age);
		}
		else{
			return Integer.compare(this.salary, otherEmp.salary);
		}
	}

}
