CREATE DATABASE IF NOT EXISTS records_db;
USE records_db;

CREATE TABLE artists (
	id		INT AUTO_INCREMENT PRIMARY KEY,
    name	VARCHAR(100) NOT NULL
);
CREATE TABLE albums (
	id				INT AUTO_INCREMENT PRIMARY KEY,
    title			VARCHAR(100) NOT NULL,
    catalogue_id	VARCHAR(20) NOT NULL,
    artist_id		INT NOT NULL,
    available		BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (artist_id) REFERENCES artists(id)
);

CREATE TABLE rent (
	id			INT AUTO_INCREMENT PRIMARY KEY,
    album_id	INT NOT NULL,
    renter_name	VARCHAR(100) NOT NULL,
    loan_date	DATE NOT NULL,
    return_date	DATE,
    FOREIGN KEY (album_id) REFERENCES albums(id)
);