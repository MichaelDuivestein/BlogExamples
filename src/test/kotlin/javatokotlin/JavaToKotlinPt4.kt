package javatokotlin

import org.junit.Test

private fun createEmployees() = listOf(
		Employee(0, 31, Department.DEV, "Bongani"),
		Employee(1, 66, Department.NOT_DEV, "Busiswe"),
		//note the two null names. One uses the default field value, which is null:
		Employee(2, 17, Department.DEV, null),
		Employee(3, 61, Department.DEV),
		Employee(4, 27, Department.NOT_DEV, "Susan"),
		Employee(5, 66, Department.NOT_DEV, "Bill")
)

class Loops {
	@Test
	fun forEachLoop() {
		val employees = createEmployees()
		employees.forEach {
			println("${it.name} is present")
		}
	}
	
	@Test
	fun traditionalJavaLoop() {
		val employees = createEmployees()
		for (counter in 0..employees.size - 1) {
			println("${employees.get(counter).name} is present")
		}
	}
	
	@Test
	fun traditionalJavaLoopStep2() {
		val employees = createEmployees()
		for (counter in 0..employees.size - 1 step 2) {
			println("${employees.get(counter).name} ($counter) is present")
		}
	}
	
	@Test
	fun untilLoop() {
		val employees = createEmployees()
		for (counter in 0 until employees.size) {
			println("${employees.get(counter).name} is present")
		}
	}
	
	@Test
	fun downToLoop() {
		val employees = createEmployees()
		for (counter in employees.size - 1 downTo 0) {
			println("${employees.get(counter).name} is present")
		}
	}
}

class When {
	fun basicWhen() {
		val employees = createEmployees()
		employees.forEach {
			when (it.name) {
				"Bongani" -> println("${it.name} is in charge")
				null -> println("Employee ${it.number} is a number, not a free man")
				//replaces "default" case
				else -> println("${it.name} is present")
			}
		}
	}
	
	@Test
	fun printWhen() {
		val employees = createEmployees()
		employees.forEach {
			println(when (it.name) {
				"Bongani" -> "${it.name} is in charge"
				null -> "Employee ${it.number} is a number, not a free man"
				//replaces "default" case
				else -> "${it.name} is present"
			})
		}
	}
	
	@Test
	fun multiValueWhen() {
		val employees = createEmployees()
		employees.forEach {
			println(when (it.name) {
				"Bongani" -> "${it.name} is in charge"
				null -> "Employee ${it.number} is a number, not a free man"
				"Busiswe", "Susan" -> "${it.name} here"
				else -> "${it.name} is present"
			})
		}
	}
	
	@Test
	fun ranges() {
		val employees = createEmployees()
		employees.forEach {
			println(when (it.age) {
				in 0..17 -> "${it.name} is Underage!"
				in 18..63 -> "${it.name} is of Working age"
				64 -> "${it.name} is Ready to retire"
				in 65..1000 -> "${it.name} is Past retirement age"
				else -> "${it.name} is Employee is either unborn or improbably old"
			})
		}
	}
	
	@Test
	fun printStatus() {
		val employees = createEmployees()
		employees.forEach {
			println(getStatus(it))
		}
	}
	
	private fun getStatus(it: Employee): String =
			when (it.age) {
				in 0..17 -> "${it.name} is Underage!"
				in 18..63 -> "${it.name} is of Working age"
				64 -> "${it.name} is Ready to retire"
				in 65..1000 -> "${it.name} is Past retirement age"
				else -> "${it.name} is Employee is either unborn or improbably old"
			}
	
	private fun smartCastingWithWhen(someObject: Any?) {
		println(when (someObject) {
			is String -> "SomeObject is a String of value \"$someObject\""
			is Int -> "SomeObject is an Int of $someObject"
			is Employee -> "SomeOject is an Employee named ${someObject.name}"
			else -> "we're not sure what the object is"
		})
	}
	
	@Test
	fun testSmartCasting() {
		smartCastingWithWhen(5)
		smartCastingWithWhen("Some String")
		smartCastingWithWhen(5L)
		smartCastingWithWhen(Employee(7, 55, Department.DEV, "Priscilla"))
		smartCastingWithWhen(null)
	}
}

sealed class WorkType(val typeName: String) {
	class DevWork(typeName: String, val nearlyDone: Boolean) : WorkType(typeName)
	class Other(typeName: String) : WorkType(typeName)
}

class HumanResources(typeName: String, val victimName: String, val fireTime: Boolean) : WorkType(typeName)
class Accounting(typeName: String, val reportsToCreate: Int) : WorkType(typeName)

data class EmployeeDoingWork(val number: Int, val age: Int, val department: Department, val name: String, val workType: WorkType)

private fun createEmployeesDoingWork() = listOf(
		EmployeeDoingWork(0, 31, Department.DEV, "Bongani",
				WorkType.DevWork("coding", false)),
		EmployeeDoingWork(1, 66, Department.NOT_DEV, "Busiswe",
				Accounting("financial reports", 7)),
		EmployeeDoingWork(4, 27, Department.NOT_DEV, "Susan",
				HumanResources("firing", "Bill", false)),
		EmployeeDoingWork(5, 66, Department.NOT_DEV, "Bill",
				WorkType.Other("cleaning"))
)

class SealedClassExamples {
	@Test
	fun doSomeWork1() {
		val employees = createEmployeesDoingWork()
		employees.forEach {
			println(when (it.workType) {
				is WorkType.DevWork -> "${it.name} is ${it.workType.typeName}. Nearly done? ${it.workType.nearlyDone}"
				else -> "${it.name} is ${it.workType.typeName}"
			})
		}
	}
	
	@Test
	fun doSomeWork2() {
		val employees = createEmployeesDoingWork()
		employees.forEach {
			println(when (it.workType) {
				is WorkType.DevWork -> "${it.name} is ${it.workType.typeName}. Nearly done? ${it.workType.nearlyDone}"
				is WorkType.Other -> "${it.name} is ${it.workType.typeName}"
				is Accounting -> "${it.name} is responsible for ${it.workType.reportsToCreate} ${it.workType.typeName}"
				is HumanResources -> "is ${it.name} ${it.workType.typeName} poor ${it.workType.victimName} yet? ${it.workType.fireTime}"
			})
		}
	}
}
