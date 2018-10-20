package javatokotlinpart2testingpackageimports

import javatokotlin.createEmployee

class TestingExternalFunctions {
	fun accessEmployeeFunction() {
		createEmployee(8, 64, "Sally", true)
	}
}