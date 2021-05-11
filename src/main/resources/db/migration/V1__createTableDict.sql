DROP TABLE IF EXISTS WEATHER_TYPE;
CREATE TABLE WEATHER_TYPE   (
  weather_Type_Cd varchar(10) PRIMARY KEY,
  temperature_Min int NOT NULL,
  temperature_Max int NOT NULL,
  IMAGE varchar(255)
);