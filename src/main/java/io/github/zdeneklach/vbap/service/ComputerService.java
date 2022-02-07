package io.github.zdeneklach.vbap.service;

import io.github.zdeneklach.vbap.entity.Computer;
import io.github.zdeneklach.vbap.exception.DbException;
import io.github.zdeneklach.vbap.repositories.ComputerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {
    ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public List<Computer> getAllComputers(){
        List<Computer> allComputers;
        try {
            allComputers = computerRepository.findAll();
        } catch (Exception e) {
            throw new DbException("Failed to retrieve all computers from DB.");
        }
        return allComputers;
    }
}
