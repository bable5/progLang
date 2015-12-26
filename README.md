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
$> java -cp target/progLang-[VERSION]-jar-with-dependencies.jar progLang.Main [SOURCE-FILE]
```

e.g.

```bash
$> java -cp target/progLang-1.0-SNAPSHOT-jar-with-dependencies.jar progLang.Main src/main/examples/ObjectLiteral.progLang
```

