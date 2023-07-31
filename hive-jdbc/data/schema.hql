
CREATE TABLE IF NOT EXISTS OIL (
    location String,
    indicator String,
    subject String,
    measure String,
    frequency String,
    mytime String,
    value Float,
    flagCode String)
  ROW FORMAT DELIMITED
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  STORED AS TEXTFILE tblproperties("skip.header.line.count"="1");


LOAD DATA INPATH 's3://bigdata-mr-lab-jus01/bigdata-mr-lab/hive/oil.csv' INTO TABLE OIL;


SELECT LOCATION,
       MIN(value) as MINPRICE,
       AVG(value) as AVGPRICE,
       MAX(value) as MAXPRICE
  FROM OIL
 WHERE FREQUENCY LIKE "A"
 GROUP BY LOCATION;

CREATE TABLE IF NOT EXISTS LATLONG (
    country String,
    alpha2 String,
    alpha3 String,
    numCode Int,
    latitude Float,
    longitude Float)
    ROW FORMAT DELIMITED
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    STORED AS TEXTFILE TBLPROPERTIES("skip.header.line.count"="1");

LOAD DATA INPATH 's3://bigdata-mr-lab-jus01/bigdata-mr-lab/hive/latlong.csv' INTO TABLE LATLONG;

select distinct *
  from (select location,
               avg(value) as avgprice
          from oil group by location) x
   left join (select trim(alpha3) as alpha3,
                     latitude,
                     longitude
                from latlong) y
     on (x.location = y.alpha3);
