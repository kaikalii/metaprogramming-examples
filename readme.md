# Code that writes code 
(that writes code?)

### Macro Metaprogramming in Multiple Languages

Kai Schmidt (she/her)

# What?

Metaprogramming is writing code that itself generates other code.

# Why?

Metaprogramming allows you to:

- Factor out common code patterns (DRY) in ways functions cannot
- Generate structures with similar patterns based on a single source of truth
- Quickly create DSLs that consisely describe data or implement functionality

# How?

Different languages have different metaprogramming facilities.

| Language   | Preproc | Templates | Eval | Tree | Hygienic? | Comptime? |
| ---------- | ------- | --------- | ---- | ---- | --------- | --------- |
| C          | ✅       |           |      |      |           |           |
| C++        | ✅       | ✅         |      |      |           |           |
| Python, JS |         |           | ✅    |      |           |           |
| Clojure    |         |           |      | ✅    |           |           |
| Rust       |         |           |      | ✅    | ✅         | ✅         |
| Zig        |         |           |      | ✅    | ✅         | ✅         |
| Uiua       |         |           |      | ✅    | ✅         | ✅         |
