drop table if exists computer cascade;

drop table if exists gameroom_to_customer cascade;

drop table if exists game_room cascade;

drop table if exists customer cascade;

drop table if exists game cascade;

drop table if exists my_user cascade;

CREATE TABLE "game_room" (
                             "game_room_id" SERIAL PRIMARY KEY,
                             "room_name" varchar
);

CREATE TABLE "computer" (
                            "computer_id" SERIAL PRIMARY KEY,
                            "computer_name" varchar,
                            "installed_game_id" int,
                            "game_room_id" int
);

CREATE TABLE "gameroom_to_customer" (
                                        "customer_id" int,
                                        "game_room_id" int
);

CREATE TABLE "customer" (
                            "customer_id" SERIAL PRIMARY KEY,
                            "first_name" varchar,
                            "surname" varchar,
                            "phone_number" varchar
);

CREATE TABLE "game" (
                        "game_id" SERIAL PRIMARY KEY,
                        "name" varchar
);

CREATE TABLE "my_user" (
                        "user_id" SERIAL PRIMARY KEY,
                        "username" varchar,
                        "password" varchar
);

ALTER TABLE "gameroom_to_customer" ADD FOREIGN KEY ("game_room_id") REFERENCES "game_room" ("game_room_id");

ALTER TABLE "gameroom_to_customer" ADD FOREIGN KEY ("customer_id") REFERENCES "customer" ("customer_id");

ALTER TABLE "computer" ADD FOREIGN KEY ("game_room_id") REFERENCES "game_room" ("game_room_id");

ALTER TABLE "computer" ADD FOREIGN KEY ("installed_game_id") REFERENCES "game" ("game_id");
