package io.github.zdeneklach.vbap;

import io.github.zdeneklach.vbap.entity.*;
import io.github.zdeneklach.vbap.repositories.*;
import io.github.zdeneklach.vbap.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VbapApplication {

    public static void main(String[] args) {
        SpringApplication.run(VbapApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            ComputerRepository computerRepository,
            CustomerRepository customerRepository,
            GameRepository gameRepository,
            UserRepository userRepository,
            GameRoomRepository gameRoomRepository,
            UserService userService
    ) {
        return args -> {
            List<GameRoom> gameRooms = List.of(
                    new GameRoom("RGB Hell"),
                    new GameRoom("Reality Evolved")
            );

            gameRoomRepository.saveAll(gameRooms);


            List<Computer> computers = List.of(
                    new Computer(gameRooms.get(0)),
                    new Computer(gameRooms.get(1)),
                    new Computer(gameRooms.get(1)),
                    new Computer(gameRooms.get(0))
            );
            List<Game> games = List.of(
                    new Game("League of Legends"),
                    new Game("Minecraft"),
                    new Game("Fortnite"),
                    new Game("Uncharted"),
                    new Game("Dying Light"),
                    new Game("Valorant")
            );

            gameRepository.saveAll(games);
            computerRepository.saveAll(computers);

            computers.get(0).installNewGame(games.get(0));
            computers.get(0).installNewGame(games.get(1));
            computers.get(0).installNewGame(games.get(4));
            computers.get(0).installNewGame(games.get(3));

            computers.get(1).installNewGame(games.get(0));
            computers.get(1).installNewGame(games.get(1));
            computers.get(1).installNewGame(games.get(2));
            computers.get(1).installNewGame(games.get(3));

            computers.get(2).installNewGame(games.get(4));
            computers.get(2).installNewGame(games.get(5));
            computers.get(2).installNewGame(games.get(2));
            computers.get(2).installNewGame(games.get(3));

            computers.get(3).installNewGame(games.get(0));
            computers.get(3).installNewGame(games.get(5));
            computers.get(3).installNewGame(games.get(2));
            computers.get(3).installNewGame(games.get(3));

            computerRepository.saveAll(computers);

            List<Customer> customers = List.of(
                    new Customer("Jan", "Šochiman", "472019284"),
                    new Customer("Marek", "Lahen", "234567891"),
                    new Customer("Pavel", "Vymyslel", "987654321")
            );
            customerRepository.saveAll(customers);

            gameRooms.get(0).assignCustomer(customers.get(0));
            gameRooms.get(0).assignCustomer(customers.get(1));
            gameRooms.get(1).assignCustomer(customers.get(1));
            gameRooms.get(0).assignCustomer(customers.get(2));

            gameRoomRepository.saveAll(gameRooms);

            List<MyUserDetails> users = List.of(
                    userService.encryptNewUserPassword(new MyUserDetails("sidonius", "password")),
                    userService.encryptNewUserPassword(new MyUserDetails("user", "user"))
            );
            userRepository.saveAll(users);
        };
    }
}
