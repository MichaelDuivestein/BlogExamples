package javatokotlin

//Note: Kotlin allows multiple public classes per file.

//declaring classes:
class Employee1() {} //constructor = ().

// Empty constructor and method body can be removed:
class Employee2

//adding params to constructor:
class Employee3(employeeName: String)

//class Developer: javatokotlin.Employee3("Samantha") //throws a compile error as javatokotlin.Employee3 is final
open class Employee4(employeeName: String)

class Developer2 : Employee4("Samantha") //call to javatokotlin.Employee4 constructor
class Developer3(val EmployeeName: String) : Employee4(EmployeeName)

class MethodsExample() {
	//declaring a methods
	fun myMethod() {}
	
	//method with parameters
	fun myMethod(someString: String, employee: Employee, someInt: Int) {}
	
	//returning params:
	fun myMethod2(): Boolean {
		return false
	}
	
	fun getEmployee(): Employee1 {
		return Employee1() //new instance of javatokotlin.Employee
	}
}

class VariablesExample1() {
	//variable declaration:
	var myMutableString: String = "Some text"
	
	//type declaration isn't needed if a variable's being assigned at declaration:
	var myMutableString2 = "Some text"
	val myFinalInt = 4
	val myFinalLong = 4L
	
	//Note the absence of the `new` keyword:
	var myEmployee = Employee1()
	
	
}

class VariablesExample2() {
	private val employeeNumber = 3 //ignore me
	
	//method declarations with parameters:
	var myEmployee2 = Employee5("Tom", employeeNumber, getDepartment())
	
	
	//Supporting stuff for above:
	class Employee5(Name: String, number: Int, department: Department)
	
	enum class Department { DEV, NOT_DEV }
	
	private fun getDepartment(): Department {
		return Department.NOT_DEV
	}
}

fun nullableVariablesExample() {
	//cannot assign a non-null  variable to null
	var myString = "Some text"
//	myString = null //throws compiler error
	
	var myString2: String? = "Some text"
	myString2 = null
//	val length = myString2.length //throws error as myString has not been null-checked
	
	//can assign the value of  a nullable variable to a non-null variable if it's been null-checked:
	var myString3: String? = "Some text"
//	var myOtherString: String = myString3 //will throw a typeMismatch error
	if (myString3 != null) {
		var myOtherString2 = myString
	}
	
	//can set a non-null variable to a nullable variable.
	var myString4 = "Some text"
	var myNullableString: String? = myString
}
