class Clock() {
  var hour: Int = 0
  var minute: Int = 0
  var second: Int = 0
  var pastSeconds: Int
    get() {
      return ((hour * 60) + minute) * 60 + second
    }
    set(value) {
      hour = value / 3600
      minute = (value % 3600) / 60
      second = value % 60
    }

  fun print() {
    println("${hour}時${minute}分${second}秒, 0時から${pastSeconds}秒経過")
  }
}

fun main(args: Array<String>) {
  val clock = Clock()
  clock.hour = 1
  clock.minute = 10
  clock.second = 5
  clock.pastSeconds = 4101
  clock.print()
}
