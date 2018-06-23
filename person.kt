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

fun main(args: Array<String>) {
  val person = Person("Tomoki")
  person.print()

  val person2 = Person("Tomoki", "Mizogami")
  person2.print()
}
