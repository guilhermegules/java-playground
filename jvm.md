# JVM

## Analyzing my JVM environment

### `java -version`

Example:

```
OpenJDK 25 LTS (Zulu)
OpenJDK 64-Bit Server VM (HotSpot)
```

| Info                 | Meaning                                     |
| -------------------- | ------------------------------------------- |
| **Java 25 LTS**      | modern version (newer than Java 21 LTS)     |
| **Zulu**             | Azul distribution (fully OpenJDK compliant) |
| **64-Bit Server VM** | JVM optimized for server workloads          |
| **HotSpot**          | most widely used JVM implementation         |
| **mixed mode**       | hybrid execution (interpreted + JIT)        |
| **sharing**          | Class Data Sharing enabled                  |

### `jps`

Output:

```
4353
13830 Jps
```

What this means:

`jps = JVM Process Status`

It lists Java processes running on the machine.

| PID   | Process                             |
| ----- | ----------------------------------- |
| 4353  | some Java process currently running |
| 13830 | the `jps` tool itself               |


### Inspecting a live JVM

Let’s discover what PID 4353 is.

Run: `jcmd 4353 VM.version`

Then: `jcmd 4353 VM.flags`

And then: `jcmd 4353 VM.command_line`

This will show:

- the real JVM version at runtime
- JVM flags in use
- which application started the JVM

## JVM Architecture

```
Class Loader
Runtime Data Areas (Memory)
Execution Engine
Native Interface (JNI)
```

## Links

- [JVM por dentro](https://vepo.medium.com/jvm-por-dentro-553bab89a027)
- [Java Virtual Machine](https://sites.google.com/site/jvmtotal/home/conceitos/java-virtual-machine)
