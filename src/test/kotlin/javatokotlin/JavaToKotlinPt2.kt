package javatokotlin

import org.junit.Assert.assertNotNull
import kotlin.test.Test

//enums in Kotlin differ from Java only by the `class` keyword
enum class Department { DEV, NOT_DEV }

//Kotlin classes
class EmployeeClass(var number: Int,
                    var age: Int,
		//default value var. Will explain later
		            var name: String = "Unknown Name",
		            var department: Department)

//Data classes
data class Employee(val number: Int, val age: Int, val department: Department, val name: String? = null)
//We'll be using the above for further examples


class EmployeeTest {
	@Test
	fun createEmployee() {
		val employee = Employee(4, 33, Department.DEV, "Alice Jacobs")
		assertNotNull(employee)
	}
	
	@Test
	fun demonstrateCopy() {
		val dave = Employee(7, 23, Department.NOT_DEV, "Dave")
		println(dave) //”javatokotlin.Employee(number=7, age=23, department=NOT_DEV, name=Dave)”
		
		val alsoDave = dave.copy()
		println(alsoDave) //”javatokotlin.Employee(number=7, age=23, department=NOT_DEV, name=Dave)”
		println(dave == alsoDave) //true
		
		val john = dave.copy(number = 8, name = "John") //named arguments are covered a bit later
		println(john) //”javatokotlin.Employee(number=8, age=23, department=NOT_DEV, name=John)”
		println(dave == john) //false
	}
}

class ConstructorsWithDefaultArgsDemonstration {
	@Test
	fun demonstrateDefaltArgs() {
		val bongani = Employee(4, 38, Department.DEV, "Bongani")
		println(bongani) //”javatokotlin.Employee(number=4, age=38, department=DEV, name=Bongani)”
		
		val namelessEmployee = Employee(4, 38, Department.DEV)
		println(namelessEmployee) //javatokotlin.Employee(number=4, age=38, department=DEV, name=null)
	}
}

data class Vehicle(val model: String,
                   val make: String,
                   val year: Int,
                   var registration: String = "Unregistered",
                   var ownerName: String? = null)

class VehicleTest {
	
	@Test
	fun createVehicle() {
		val vehicle = Vehicle("Tesla", "javatokotlin.Moonshot", 2020)
		println(vehicle) //"javatokotlin.Vehicle(model=Tesla, make=javatokotlin.Moonshot, year=2020, registration=Unregistered, ownerName=null)"
	}
}

class DefaultArgsDemo2 {
	fun doStuff(id: Int, age: Int = 0, name: String = "not yet named", occupation: String? = null) {
		//String interpolation. Covered in part 3.
		println("ID = $id, age = $age, name: $name, occupation: $occupation")
	}
	
	@Test
	fun demonstrateDoStuff() {
		doStuff(0, 14, "Cecilia", "Telemarketer")
		doStuff(0, 1)
	}
}

open class Vehicle2(val model: String,
                    val make: String,
                    open var year: Int,
                    var registration: String = "Unregistered",
                    var ownerName: String? = null)

class Moonshot(override var year: Int) : Vehicle2("Tesla", "javatokotlin.Moonshot", year) //no registrationName or ownerName

class ConstructorsAndMethodsWithNamedArguments {
	
	@Test
	fun demonstrateNamedArgs() {
		val sally = Employee(name = "Sally", age = 38, department = Department.NOT_DEV, number = 4)
		println(sally)
		
		createEmployee(number = 4, department = Department.NOT_DEV, name = "Sally", age = 38)
	}
	
	//method to demonstrate above
	private fun createEmployee(number: Int,
	                           age: Int,
	                           name: String?/*("?") means this variable is nullable*/,
	                           department: Department): Employee {
		return Employee(number, age, department, name)
	}
}

class SingleLineMethods {
	fun isEmployeeADeveloper(employee: Employee): Boolean {
		return Department.DEV == employee.department
	}
	
	fun isEmployeeADeveloperSingleLine(employee: Employee) = Department.DEV == employee.department
}

class EmployeeManager {
	val employees = ArrayList<Employee>()
	fun canGrantSudoRights(employee: Employee) = verifyIsDev(employee)
	companion object {
		val devOrdinal = Department.DEV.ordinal
		fun verifyIsDev(employee: Employee): Boolean = devOrdinal == employee.department.ordinal
	}
}

//also demonstrates functions outside of classes
fun companionObjectAccess() {
	val employee = Employee(19, 63, Department.NOT_DEV, "Sue Ramsey")
	val isDev = EmployeeManager.verifyIsDev(employee)
}

fun createEmployee(number: Int, age: Int, employeeName: String?, isDev: Boolean = true): Employee {
	
	//in-line If statement. This replaces Java's "? :"
	return Employee(number, age, if (isDev) Department.DEV else Department.NOT_DEV, employeeName)
}