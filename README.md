#Getting Started

Install antrl [4.3](https://github.com/antlr/website-antlr4/blob/gh-pages/download/antlr-4.3-complete.jar?raw=true) tools. Version 4.3 is newest version with maven tooling.

Setup your environment (may need to customize the .env file for your setup.)

```bash
$> source .env
```

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

