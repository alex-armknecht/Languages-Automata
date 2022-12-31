import Foundation

// Problem 1
enum NegativeAmountError: Error {
  case negativeAmount
}

func change(_ amount: Int) -> Result<(Int, Int, Int, Int), NegativeAmountError> {
  if amount < 0 {
    return .failure(NegativeAmountError.negativeAmount)
  }
    
  let (quarters, leftOverFromQuarters) = amount.quotientAndRemainder(dividingBy: 25)
  let (dimes, leftOverFromDimes) = leftOverFromQuarters.quotientAndRemainder(dividingBy: 10)
  let (nickels, leftOverFromNickels) = leftOverFromDimes.quotientAndRemainder(dividingBy: 5)
  
  return .success((quarters, dimes, nickels, leftOverFromNickels))
}

// Problem 2
extension String{
  var stretched: String {
    var noWhitespace = self
    var stretchedString: String = ""
    var index: Int = 0
    let whitespace: Set<Character> = [" ", "\t", "\n"]
    noWhitespace.removeAll(where: { whitespace.contains($0) })
    for character in noWhitespace{
      index += 1
      stretchedString += String(repeating: character, count: index)
    }
    return stretchedString
  }
}

// Problem 3
extension Array {
  func mapThenUnique<T>(transform: (Element) -> T) -> Set<T> {
    return Set(self.map(transform))
  }
}

// Problem 4
func powers(of: Int, through: Int, consume: (Int) -> Void) {
  var power = 1
  while power <= through {
    consume(power)
    power *= of
  }
}

// Problem 5
protocol Animal {
  var name: String { get }
  var sound: String { get }
}

extension Animal {
  func speak() -> String {
    return "\(name) says \(sound)"
  }
}

struct Horse: Animal {
  var name: String
  var sound: String = "neigh"
}

struct Cow: Animal {
  var name: String
  var sound: String = "moooo"
}

struct Sheep: Animal {
  var name: String
  var sound: String = "baaaa"
}

// Problem 6
class say {
  var phrase: String
  init(_ word: String){
    self.phrase = word
  }

  func and(_ nextWord: String) -> say {
    return say(self.phrase + " " + nextWord)
  }
}

// Problem 7
func twice<T>(_ f: (T) -> T, appliedTo x: T) -> T {
    return f(f(x))
}

// Problem 8
func uppercasedFirst(of words: [String], longerThan val: Int) -> String? {
  let firstValMatch = words.first(where: { $0.count > val })?.uppercased()
  return firstValMatch
}

// Problem 9
struct Quaternion {

    var a: Double
    var b: Double
    var c: Double
    var d: Double
    var coefficients: [Double]
    static let I = Quaternion(a: 0, b: 1, c: 0, d: 0)
    static let J = Quaternion(a: 0, b: 0, c: 1, d: 0)
    static let K = Quaternion(a: 0, b: 0, c: 0, d: 1)
    static let ZERO = Quaternion(a: 0, b: 0, c: 0, d: 0)

    init(a: Double, b: Double, c: Double, d: Double) {
        self.a = a
        self.b = b
        self.c = c
        self.d = d
        self.coefficients = [a, b, c, d]
    }

    static func +(left: Quaternion, right: Quaternion) -> Quaternion{
        return Quaternion(
            a: left.a + right.a,
            b: left.b + right.b,
            c: left.c + right.c,
            d: left.d + right.d)
    }
    
    static func -(left: Quaternion, right: Quaternion) -> Quaternion{
        return Quaternion(
            a: left.a - right.a,
            b: left.b - right.b,
            c: left.c - right.c,
            d: left.d - right.d)
    }

    static func *(left: Quaternion, right: Quaternion) -> Quaternion{
        return Quaternion(
            a:  left.a * right.a -
                left.b * right.b -
                left.c * right.c -
                left.d * right.d,
            b:  left.a * right.b +
                left.b * right.a +
                left.c * right.d -
                left.d * right.c,
            c:  left.a * right.c -
                left.b * right.d +
                left.c * right.a +
                left.d * right.b,
            d:  left.a * right.d +
                left.b * right.c -
                left.c * right.b +
                left.d * right.a)
    }

}

extension Quaternion: CustomStringConvertible {
      var description: String {
        var bSign = ""
        if b >= 0 {bSign = "+"}
        var cSign = ""
        if c >= 0 {cSign = "+"}
        var dSign = ""
        if d >= 0 {dSign = "+"}
        return "\(a)\(bSign)\(b)i\(cSign)\(c)j\(dSign)\(d)k"
      }
}

extension Quaternion: Equatable {
  static func == (left: Quaternion, right: Quaternion) -> Bool {
    return left.a == right.a && left.b == right.b && left.c == right.c && left.d == right.d
  }
}