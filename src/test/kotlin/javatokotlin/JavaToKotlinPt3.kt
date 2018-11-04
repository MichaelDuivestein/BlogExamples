package javatokotlin

import org.junit.Test
import java.lang.Math.round

//Please note that `@Test` is abused here. It has been used as a shortcut for demonstrating things.
// Doing this on a real app should be considered illegal.

class VisibilityModifiers {
	public var publicVar = "I'm public, so can be accessed by anything" //Note: "public" is actually redundant:
	var defaultVar = "I'm also public, and I'm Kotlin's preferred variable state"
	protected var protectedVar = "I'm protected, so I'm only visible to this class an child classes"
	private var privateVar = "I'm private, which means I'm only visible to code inside this file"
	internal var internalVar = "I'm internal, so I'm only visible to code in the same module"
	
}

const val PUBLIC_CONSTANT = 12
internal const val INTERNAL_CONSTANT = 4
private const val PRIVATE_CONSTANT = 149


class GettersAndSetters {
	var department: Department? = null
		get() = if (field != null) field else Department.NOT_DEV
		set(department) {
			field = if (department != null) department else Department.DEV
		}
}

class GettersAndSettersTest {
	@Test
	fun showcaseGetterAndSetterOverride() {
		val getters = GettersAndSetters() //department initialised with `null`
		
		println("Department is ${getters.department}") //"Department is NOT_DEV"
		getters.department = null //setter will default to `DEV`
		println("Department is ${getters.department}") //"Department is DEV"
	}
}

class CustomGettersAndSetters {
	private var department: Department? = null
	
	var isDev: Boolean
		get() = department == Department.DEV
		set(value) = if (value) department = Department.DEV else department = Department.NOT_DEV
}

class CustomGetterAndSetterTest {
	@Test
	fun showcaseGettersAndSetters() {
		val customGetter = CustomGettersAndSetters() //department initialised with `null`
		
		println("isDev = ${customGetter.isDev}")//"isDev = false"
		customGetter.isDev = true
		println("isDev = ${customGetter.isDev}") //"isDev = true"
	}
}

class ElvisExpression {
	//same as GettersAndSetters except an elvis expression is used on the setter
	var department: Department? = null
		get() {
			return if (field != null) field else Department.NOT_DEV
		}
		set(department: Department?) {
			field = department ?: Department.DEV
		}
}

class NotNullAssertion {
	@Test(expected = KotlinNullPointerException::class)
	fun `asserting not null on a null val throws a KotlinNullPointerException`() {
		val nullableString: String? = null
		val length = nullableString!!.length
	}
}

class Lateinit {
	lateinit var lateVar: String
}

class LateinitTest {
	@Test(expected = UninitializedPropertyAccessException::class)
	fun `accessing lateinitVar without being set should throw an UninitializedPropertyAccessException`() {
		println(Lateinit().lateVar.length)
	}
	
	@Test
	fun ddd() {
		val lateInit = Lateinit()
		lateInit.lateVar = "I'm initialized"
		println(lateInit.lateVar.length)
	}
}

class ByLazy() {
	val employee: Employee by lazy { Employee(0, 64, Department.NOT_DEV, "Kevin") }
	//this will only be loaded when accessed.
}

class StringInterpolation {
	@Test
	fun basicInterpolation() {
		val numberOfEmployees = 4
		println("There are currently $numberOfEmployees employees in the company")
	}
	
	@Test
	fun methodCallsInInterpolation() {
		val employee = Employee(4, 32, Department.DEV, "Sarah")
		println("Employee ${employee.number} is ${employee.age} years old")
	}
	
	@Test
	fun testAdd() {
		println("14 + 6 = ${14 + 6}")
		println("$PUBLIC_CONSTANT * $INTERNAL_CONSTANT = ${PUBLIC_CONSTANT * INTERNAL_CONSTANT}")
	}
	
	@Test
	fun testExpressions() {
		println("${if (PUBLIC_CONSTANT < INTERNAL_CONSTANT) "PUBLIC_CONSTANT (val $PUBLIC_CONSTANT)"
		else "INTERNAL_CONSTANT, val $INTERNAL_CONSTANT"} is smaller")
		
		println("${if (PUBLIC_CONSTANT > INTERNAL_CONSTANT) "PUBLIC_CONSTANT (val $PUBLIC_CONSTANT)"
		else "INTERNAL_CONSTANT, val $INTERNAL_CONSTANT"
		} is greater than ${
		if (INTERNAL_CONSTANT > PUBLIC_CONSTANT) "PUBLIC_CONSTANT, val $INTERNAL_CONSTANT"
		else "INTERNAL_CONSTANT (val $INTERNAL_CONSTANT)"}")
	}
}

class JoinToString {
	@Test
	fun joinToStringExample() {
		val numbers = doubleArrayOf(4.41, 6.399999, 12.0, 14.46, 17.171717171)
		
		println("Straight join: ${numbers.joinToString()}")
		
		println("Specifying separator: ${numbers.joinToString(separator = " ~ ")}")
		
		println("Specifying prefix and suffix: ${numbers.joinToString(prefix = "{", postfix = "}")}")
		
		println("Limiting number of elements: ${numbers.joinToString(limit = 3)}")
		
		println("Rounding using transform(): ${numbers.joinToString(transform = {round(it).toString()})}")
		
		println("Limiting number of elements And setting truncate: ${numbers.joinToString(limit = 3, truncated = "etc.")}")
		
		println("Specifying everything: ${numbers.joinToString(separator = " ~ ", prefix = "{", postfix = "}", transform = {round(it).toString()}, limit = 3, truncated = "etc.")}")
	}
}

class Equality {
	@Test
	fun testingStringEquals() {
		val text = "SomeText"
		println("SomeText" == text) //this replaces .equals()
	}
}

class SmartCasting {
	private fun smartCasting(someObject: Any?) {  //Any is the root class of all Kotlin objects
		if (someObject is String) {
			println("SomeObject is a String of value \"$someObject\" ")
		} else if (someObject is Int) {
			println("SomeObject is an Int of $someObject")
			println(someObject + 4)
		} else if (someObject is Employee) {
			println("SomeObject is an Employee named ${someObject.name}")
		} else {
			println("we're not sure what it is")
		}
		//the above structure can be converted to `when`. This is covered in Part 4.
	}
	
	@Test
	fun testSmartCasting() {
		smartCasting(5)
		smartCasting("Some String")
		smartCasting(5L)
		smartCasting(Employee(3, 55, Department.DEV, "Priscilla"))
		smartCasting(null)
	}
	
}
