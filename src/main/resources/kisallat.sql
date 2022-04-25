CREATE TABLE IF NOT EXISTS kisallat (
  id integer PRIMARY KEY AUTOINCREMENT,
  nev text,
  fajta text,
  kor integer
);
INSERT INTO kisallat (nev, fajta, kor) VALUES ("Cirmi", "Macska", 2);
INSERT INTO kisallat (nev, fajta, kor) VALUES ("Morzsi", "Kutya", 3);
