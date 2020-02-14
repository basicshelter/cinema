
CREATE TABLE IF NOT EXISTS Director(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR (50) NOT NULL, 
    last_name VARCHAR (50) NOT NULL, 
    birth_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS Film (
    id SERIAL PRIMARY KEY, 
    director_id INTEGER REFERENCES Director(id), 
    name VARCHAR (50) NOT NULL, 
    release_date DATE NOT NULL, 
    genre VARCHAR (50) NOT NULL
);