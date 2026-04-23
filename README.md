Since this is for a lab submission, a "bigger" README usually means adding more technical depth, explaining the underlying theory (Chomsky hierarchy, Automata), and providing a clear guide on how to run the code.

Here is a comprehensive, professional `README.md` for **Variant 2**.

---

# Regular Expression Interpreter & String Generator
**Course:** Formal Languages and Automata Theory  
**Lab Assignment:** Dynamic Regex Parsing  
**Student:** [Your Name]  
**Variant:** 2  

---

## 1. Project Overview
This project implements a custom **Regular Expression Engine** in Java. Unlike standard libraries that use regex for pattern matching, this tool interprets regex syntax **dynamically** to generate valid string combinations (sentences) that belong to the language defined by the expression.

### Core Objectives:
* Deconstruct complex regular expressions into tokens.
* Handle quantifiers, alternations, and grouping.
* Generate valid outputs without hardcoding logic for specific patterns.

---

## 2. Theoretical Background

### What are Regular Expressions?
In formal language theory, a **Regular Expression** is a way to describe a **Regular Language**. These languages are at the lowest level of the *Chomsky Hierarchy* (Type 3) and can be recognized by **Finite State Automata (FSA)**.

### Common Symbols & Operators:
| Symbol | Name | Description |
| :--- | :--- | :--- |
| `(A\|B)` | **Alternation** | Acts as a logical OR; chooses either $A$ or $B$. |
| `?` | **Optionality** | The preceding element occurs 0 or 1 time. |
| `*` | **Kleene Star** | The preceding element occurs 0 or more times. |
| `+` | **Kleene Plus** | The preceding element occurs 1 or more times. |
| `^n` | **Numeric Power** | The preceding element is repeated exactly $n$ times. |
| `.` | **Concatenation** | (Implicit) Symbols placed next to each other. |

---

## 3. Architecture & Implementation

The solution follows a **Modular Design** to separate the parsing logic from the execution.

### A. `RegexEngine.java` (The Interpreter)
The engine uses a **Linear Scanning Algorithm**. It reads the input string from left to right, identifying "Atoms" and "Quantifiers."
* **Atoms:** The smallest unit of a regex—either a single character or a sub-expression inside parentheses `()`.
* **Lookahead Logic:** When an atom is identified, the engine "peeks" at the next character to see if a quantifier follows it.
* **Randomization:** For non-deterministic operators (`?`, `*`, `+`, `|`), the engine uses `java.util.Random` to select a valid path.

### B. `Main.java` (The Controller)
This serves as the entry point. It defines the specific expressions for **Variant 2** and manages the output formatting for the lab report.

---

## 4. Sequence of Processing (Bonus Logic)
The engine respects the standard order of operations in formal grammar:
1.  **Parentheses (Grouping):** Higher priority. Groups are resolved into a single chosen string before quantifiers are applied.
2.  **Quantification:** Operators like `^n`, `*`, and `+` bind strictly to the atom immediately to their left.
3.  **Concatenation:** Atoms and their repetitions are joined together in sequence.
4.  **Alternation:** The `|` operator within a group has the lowest priority during group resolution.

---

## 5. Variant 2 Patterns & Analysis

The following expressions were processed:

1.  **`M?N^2(O|P)^3Q*R+`**
    * *Logic:* Optional M, exactly two Ns, three choices of O or P, variable Qs, and at least one R.
2.  **`(X|Y|Z)^38+(9|0)`**
    * *Logic:* Exactly three characters (X, Y, or Z), followed by one or more 8s, ending with 9 or 0.
3.  **`(H|i)(J|K)L*N?`**
    * *Logic:* Choice of H or i, choice of J or K, variable Ls, and an optional N.

---

## 6. Challenges Faced & Solutions

### The Greedy Quantifier Issue
In the expression `(X|Y|Z)^38+(9|0)`, a challenge arose where the parser initially read `^38` as a single instruction to repeat 38 times.
* **Solution:** Implemented a digit-limit check in the `extractPower` method. The parser was adjusted to recognize that if a digit is followed by another quantifier (like `+`), the previous digit belongs to the `^` power, and the current digit is a new literal atom.

### Repetition Limits
To prevent "Infinite Loops" or extremely long strings from `*` and `+` operators, a constant `MAX_LIMIT = 5` was implemented. This ensures the generated strings remain readable and memory-efficient while still demonstrating the logic of the regex.

---

## 7. How to Run
1.  Ensure you have **JDK 8 or higher** installed.
2.  Place `RegexEngine.java` and `Main.java` in the same directory.
3.  Compile the code:
    ```bash
    javac Main.java RegexEngine.java
    ```
4.  Run the application:
    ```bash
    java Main
    ```

---

## 8. Conclusion
The implementation successfully demonstrates how a high-level string can be parsed into tokens and used to generate a valid language. The engine is flexible enough to handle any standard regular expression within the lab's scope.
