# Introduction

Hive JDBC access demonstration.


## Dependency

    <dependency>
      <groupId>org.apache.hive</groupId>
      <artifactId>hive-jdbc</artifactId>
      <version>3.1.3</version>
    </dependency>

## Prep

Create a table named `pokes` as follows:

    CREATE TABLE IF NOT EXISTS pokes (
        foo int,
        bar string,
        new_col int
    );

Populate some data:

    insert into pokes(foo, bar, new_col) values(10, 'cryptography', 1);
    insert into pokes(foo, bar, new_col) values(20, 'steganography', 1);
    insert into pokes(foo, bar, new_col) values(30, 'HMAC', 1);
    insert into pokes(foo, bar, new_col) values(40, 'non-repudiation', 1);
    insert into pokes(foo, bar, new_col) values(50, 'VPC', 1);

The follow HQL is very inefficient, for exploration purpose only:

    insert into pokes(foo, bar, new_col)
    select 60, 'IP-SEC', 1 union
    select 70, 'Quantum security', 1 union
    select 80, 'Man-in-middle', 1
    ;
