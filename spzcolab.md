## 自己紹介
- 藤田琢磨さん
- FOLIO
  - シリーズA2
  - オンライン証券

## Kotlin とは？

- Android の First Language
- 2012 M1
- 2016 1.0
- LLVM
- Support JS
- 2017年の5月，Google サポート

- Lambdas
- Nullable (NonNull)
- High-Order Functions (Stream)
- Extension Functions : 拡張関数って何

## Kotlin の学習

- Slack
  - kotlinlang
  - kotlinlang-jp
- Try Kotlin
  - https://try.kotlinlang.org
- デコンパイル
  - Kotlin を Java のコードに変換する
- JS, Server
  - [kotlin-fullstack-sample](https://github.com/Kotlin/kotlin-fullstack-sample)
  - kotlin-conf
- 本
  - 助走読本
  - Kotlin（赤べこ本）

## 今日のゴール

- スコープ関数
  - 拡張関数：ある関数を，ある関数にくっつけて拡張する
  - apply
  - let
  - run
  - with

## 内容

### 変数

```kotlin
val num : Int = 1
var num2 : Int = 2
num = 3 // error
num2 = 3 // OK
```

- val は再代入不可
- var は再代入可能
- 型推論ができるので

```kotlin
val num = 1
var num2 = 2
```

とできます．チーム開発のときはルール決めしましょう．Kotlin のサイトにヒントが書いてあります．

### Null 安全

```kotlin
var a: String = "abc"
a = null // error when compiling

// ↓

var b: String? = "abc"
b = null // OK
```

```kotlin
var b: String? = "abc"
val l : Int = b.length // error when compiling


// ↓
var b: String? = "abc"
val l : Int = if (b != null) {
  b.length // smart cast (b is not null!!!!!!!)
} else {
  -1
}

// or
val l : Int? = b?.length // when b is null then l is null
```

- if は式なので，if() { b.length } のように値を返す．例えば，

```kotlin
val l : Int = if(b != null)
b.length else -1

// or
val l : Int = b?.length ?: -1
```
のように省略してかける．．

- ?: は，エルビス演算子という．

### 関数

- 関数定義

```kotlin
fun add(x: Int, y: Int) : Int {
  return x + y
}
```

- ブロックなしで

```kotlin
fun add(x: Int, y* int) = x + y
```

と書ける．

### 高階関数

- 関数の中で関数を実行する

```kotlin
fun <T> lock(lock: Lock, body: () -> T): T {
  lock.lock()
  try {
    return body()
  } finally {
    lock.unlock()
  }
}
```

- `body: () -> T` について，`()` が引数を表しており，`T` が戻り値を表す．
  - 引数なし

### ラムダ式

```kotlin
max(strings, {a, b -> a.length < b.length })

max(strings) {a, b -> a.length < b.length }
```

- { } 内は複数行になる場合があるので，ブロックを外に出すことができる

### 拡張関数

- 次の関数

```kotlin
"hoge".isEmpty()
```

は，

```kotlin
@kotlin.internal.InlineOnly
public inline fun CharSequence.isEmpty():
Boolean = length == 0
```

のように定義されている．`CharSequence` はレシーバと呼ばれる．文字列をレシーバとする `isEmpty()` という関数を定義した．
ここで，`this.length` でなく `length` となっているのは，`this` はレシーバオブジェクトに対応しているので，省略しているのである．

- 次の関数

```kotlin
1234.withComma() // => 1,234
```

```kotlin
fun Int.withComma(): String =
  NumberFormat.getNumberInstance().format(this)
```

のように定義できる．this は1234

### スコープ関数

#### run

- 任意の型 T をレシーバとして任意の型 R を返す関数

```kotlin
public inline fun <T, R> T.run(block: T.() -> R): R = block()

val s = "hoge".run { toUpperCase() }
// 本来は this.toUpperCase() なのを省略している
// T = CharString
println(s) // => HOGE
```

#### apply

```kotlin
public inline fun <T> T.apply(block: T.() -> Unit): T { block();
return this }
// レシーバオブジェクトを返しているので，
//  return this をすると "hoge" がそのまま返ってくる

val s = "hoge".apply { toUpperCase() }
println(s) // => hoge
```

- これによって何が嬉しいのか

```kotlin
val lp = view.layourParams
lp.height = lp.width /2
view.layoutParams = lp

// ↓

view.layoutParams = view.layoutParams.apply { height = width / 2 }
// オブジェクト自体は変更されていないが，height と width は変更されているので，1行で書ける
```

### まとめ

- スコープ関数はただの拡張関数

## 質疑応答

- なぜ Kotlin なのか？
  - 型がしっかりしていて，Java より書きやすい
- Kotlin はどんな風にマルチプラットフォームにできる？
  - Spring フレームワークも対応してます
  - iOS はまだかなぁ
  - Web もまだかなぁ
- Code > Convert Java File to Kotlin File
