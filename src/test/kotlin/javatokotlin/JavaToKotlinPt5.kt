package javatokotlin

import org.junit.Test

class EmployeeBuilder {
	var number = 0
	var age = 0
	var name: String? = null
	lateinit var department: Department
	
	fun buildEmployee(): Employee {
		return Employee(number, age, department, name)
	}
}

class VanillaBuildEmployeeDemonstration {
	fun buildEmployee(): Employee {
		val employeeBuilder = EmployeeBuilder()
		employeeBuilder.number = 13
		employeeBuilder.age = 46
		employeeBuilder.department = Department.NOT_DEV
		employeeBuilder.name = "Jaim"
		return employeeBuilder.buildEmployee()
	}
	
	@Test
	fun testBuildEmployee() {
		val employee = buildEmployee()
		println(employee.toString())
		//Employee(number=13, age=46, department=NOT_DEV, name=Mark)
	}
}

class WithDemonstration {
	fun buildEmployee(): Employee {
		val employeeBuilder = EmployeeBuilder()
		with(employeeBuilder) {
			number = 319
			age = 55
			name = "Zanile"
			department = Department.DEV
		}
		return employeeBuilder.buildEmployee()
	}
	
	@Test
	fun testBuildEmployee() {
		val employee = buildEmployee()
		println(employee.toString())
		//Employee(number=319, age=55, department=DEV, name=Zanile)
	}
	
	
	fun buildEmployeeInsideWith(): Employee {
		val employeeBuilder = EmployeeBuilder()
		return with(employeeBuilder) {
			number = 319
			age = 55
			name = "Zanile"
			department = Department.DEV
			buildEmployee()
		}
	}
	
	@Test
	fun testBuildEmployeeInsideWith() {
		val employee = buildEmployeeInsideWith()
		println(employee.toString())
		//Employee(number=319, age=55, department=DEV, name=Zanile)
	}
	
	
	fun inlineBuildEmployee(): Employee =
			with(EmployeeBuilder()) {
				number = 319
				age = 55
				name = "Zanile"
				department = Department.DEV
				buildEmployee()
			}
	
	@Test
	fun testInlineBuildEmployee() {
		val employee = inlineBuildEmployee()
		println(employee.toString())
		//Employee(number=319, age=55, department=DEV, name=Zanile)
	}
}

class ApplyDemonstration {
	fun buildEmployee(): Employee {
		val employeeBuilder = EmployeeBuilder().apply {
			number = 18
			age = 32
			name = "Martha"
			department = Department.NOT_DEV
		}
		return employeeBuilder.buildEmployee()
	}
	
	@Test
	fun testBuildEmployee() {
		val employee = buildEmployee()
		println(employee.toString())
		//Employee(number=18, age=32, department=NOT_DEV, name=Martha)
	}
	
	fun chainBuildEmployee() = EmployeeBuilder().apply {
		number = 18
		age = 32
		name = "Martha"
		department = Department.NOT_DEV
	}.buildEmployee()
	
	@Test
	fun testChainBuildEmployee() {
		val employee = chainBuildEmployee()
		println(employee.toString())
		//Employee(number=18, age=32, department=NOT_DEV, name=Martha)
	}
}

class RunDemonstration {
	fun buildEmployee() = EmployeeBuilder()
			.run {
				number = 217
				age = 19
				name = "John"
				department = Department.NOT_DEV
				buildEmployee()
			}
	
	@Test
	fun testBuildEmployee() {
		val employee = buildEmployee()
		println(employee.toString())
		//Employee(number=217, age=19, department=NOT_DEV, name=John)
	}
}

class LetDemonstration {
	fun buildEmployee() = EmployeeBuilder()
			.let {
				it.number = 7
				it.age = 21
				it.name = "Carey"
				it.department = Department.DEV
				it.buildEmployee()
			}
	
	@Test
	fun testBuildAndRunEmployee() {
		val employee = buildEmployee()
		println(employee.toString())
		//Employee(number=7, age=21, department=DEV, name=Carey)
	}
}

class AlsoDemonstration {
	fun buildEmployee() = EmployeeBuilder().also {
		it.number = 2685
		it.age = 18
		it.name = "Wesley"
		it.department = Department.NOT_DEV
	}.buildEmployee()
	
	@Test
	fun testBuildEmployee() {
		val employee = buildEmployee()
		println(employee.toString())
		//Employee(number=2685, age=18, department=NOT_DEV, name=Wesley)
	}
}
