#Getting Started

Generate Java source from grammar files

```bash
$> mvn antlr4:antlr4
```

# Running the compiler

Create a jar file with

```bash
$> mvn package
```

Run the jar:

```bash
$> java -cp target/progLang-[VERSION]-jar-with-dependencies.jar [SOURCE-FILE]
```

e.g.

```bash
$> java -cp target/progLang-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/examples/ObjectLiteral.progLang
```

