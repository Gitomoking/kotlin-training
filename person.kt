class Person(name: String) {
  // Property
  var firstName: String
  var lastName: String

  // Primary Constructor
  init {
    this.firstName = name
    this.lastName = ""
  }

  // Secondary Constructor
  constructor(firstName: String, lastName: String): this(firstName) {
    this.lastName = lastName
  }

  fun print() {
    println("お名前: ${firstName} ${lastName}")
  }
}

class Person2(val firstName: String, val lastName: String = "") {
  fun print() {
    println("お名前: ${firstName} ${lastName}")
  }
}

fun main(args: Array<String>) {
  val person = Person("Tomoki")
  person.print()

  val person2 = Person("Tomoki", "Mizogami")
  person2.print()

  val person3 = Person2("Tomoki")
  person3.print()

  val person4 = Person2("Tomoki", "Mizogami")
  person4.print()
}
