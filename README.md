[![progress-banner](https://backend.codecrafters.io/progress/interpreter/45e2d240-e202-410b-8532-48b646ec6bcb)](https://app.codecrafters.io/users/codecrafters-bot?r=2qF)

# Lox Interpreter

This project is a Java implementation of the Lox interpreter, built as part of the Codecrafters "Build your own Interpreter" challenge. The challenge follows the book [Crafting Interpreters](https://craftinginterpreters.com/) by Robert Nystrom.

## Overview

Lox is a simple scripting language. This interpreter project covers various aspects of language implementation, including tokenization, parsing, and interpreting the abstract syntax tree (AST).

## Features
- **Tokenization** – `Scanner` converts source code into tokens (keywords, identifiers, literals, operators).
- **Parsing** – `Parser` converts tokens into an AST, handling expressions and statements.
- **AST** – `Expr` and `Stmt` define the structure of Lox code.
- **Interpretation** – `Interpreter` walks the AST to execute code (variables, functions, control flow).
- **Error Handling** – Handles syntax and runtime errors.
- **Environment & Scope** – `Environment` manages nested scopes for variables and functions.
- **Function Calls** – `Function` supports parameter passing and return values.
- **Native Functions** – Includes `clock` to return the current time.

## Usage

### Running the Interpreter Locally
To run the interpreter locally, use the `your_program.sh` script:

```sh
your_program.sh run <filename>
```

### Commands

| Command               | Description                                          |
| --------------------- | ---------------------------------------------------- |
| `tokenize <filename>` | Tokenizes the source code and prints the tokens.     |
| `parse <filename>`    | Parses the source code and prints the AST.           |
| `evaluate <filename>` | Evaluates a single expression and prints the result. |
| `run <filename>`      | Runs the entire Lox program.                         |


### Example
To run a Lox program:

```sh
your_program.sh run test.lox
```

## Acknowledgments
- This project follows the book [Crafting Interpreters](https://craftinginterpreters.com/) by Robert Nystrom. Special thanks to Codecrafters for providing the challenge.
- For more details, visit the [Codecrafters Challenge](https://app.codecrafters.io/courses/interpreter/overview) page.
