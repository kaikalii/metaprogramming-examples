# Code that writes code 
(that writes code?)

### Metaprogramming in Multiple Languages

Kai Schmidt (she/her)

# What?

Metaprogramming means different things for different contexts, languages, and people.

For our purposes here, metaprogramming is **writing code that generates other code**.

# Why?

Metaprogramming allows you to:

- Factor out common code patterns (DRY) in ways functions cannot
- Generate code structures with similar patterns based on a single source of truth
- Quickly create DSLs that consisely describe data or implement functionality

# How?

Different languages have different metaprogramming facilities:

### Just write a program that generates code

- ✅ Works with any language
- 🙁 Tooling cannot see the generated language
- 🙁 Annoying to write
- 🙁 Has to be run in a separate step

### Preprocessor

- A preprecessor language sits in a layer above the main language and can conditionally emit code.
- ✅ Powerful 
- 🙁 Annoying to write
- 🙁 Unhiegenic

### `eval()` and `exec()`

- Functions that take a string and run it as code
- ✅ Common in many dynamic languages
- 🙁 Tooling cannot see the generated code
- 🙁 Annoying to write code for
- Security concerns, though not relevant for metaprogramming

### Same-Language Macros

- Transformation and generation of code via the language itself
- ✅ Very powerful
- 🙁 Can be cumbersome to write

### DSL Macros

- Transformation and generation of code via a dedicated macro language
- ✅ More integrated than a preprocessor
- ✅ Generally easier to write than same-language macros
- 🫤 Generally less powerful than same-language macros

## Metaprogramming features for select languages

| Language | Preproc | `eval()` | In-Lang Macros | DSL Macros | Hygienic? | Comptime? | Tooling? |
| -------- | ------- | -------- | -------------- | ---------- | --------- | --------- | -------- |
| C        | ✅       |          |                |            |           | ✅         | ✅        |
| C++      | ✅       |          |                |            |           | ✅         | ✅        |
| Python   |         | ✅        |                |            |           |           |          |
| JS       |         | ✅        |                |            |           |           |          |
| Clojure  |         |          | ✅              |            | 🤏         | ✅         | 🤏        |
| Rust     |         |          | ✅              | ✅          | ✅         | ✅         | ✅        |
| Zig      |         |          | ✅              |            | ✅         | ✅         | 🤏        |
| Uiua     |         |          | ✅              | 🤏          | ✅         | ✅         | 🤏        |

# Motivating Example: Vector Types

Make multiple mathematical vector types that:
- Have different names
- Have different numbers of dimensions
- Have the same operations

Maybe you're making a graphics or game library or something.

How are you going to implement all math ops, multiple constructors, `length`, `normalize`, etc for all of `Vec2`, `Vec3`, `Vec4`, `Size`, `Color`, etc?

**Don't do it by hand.** It will take too long and won't be very fun.

**Don't have an LLM do it.** When you inevitably have to change or add something, you either have to update every type by hand or spend valuable tokens reading a ton of code. Also you would rob yourself of the joy of creation.

**Use metaprogramming instead!**

### Demo
