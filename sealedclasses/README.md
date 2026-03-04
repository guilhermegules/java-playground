# Sealed Classes

## Basic Syntax

```java
public sealed class Shape
    permits Circle, Square {
}
```

Subclasses must declare one of:

- final - stop inheritance
- sealed - controlled inheritance
- non-sealed - open it again

## ⚠ Rules & Limitations

Permitted subclasses must:

- Be in the same package (or same module)
- Subclasses must declare:
    - final, sealed, or non-sealed
- Cannot be anonymous classes

## Sealed vs Enum

| Enum                            | Sealed Class            |
| ------------------------------- | ----------------------- |
| Fixed constants                 | Real class hierarchy    |
| Cannot hold complex inheritance | Full OOP support        |
| Simple state                    | Complex domain modeling |

