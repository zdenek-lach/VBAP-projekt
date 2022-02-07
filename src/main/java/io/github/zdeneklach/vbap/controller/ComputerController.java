package io.github.zdeneklach.vbap.controller;

import io.github.zdeneklach.vbap.entity.Computer;
import io.github.zdeneklach.vbap.entity.Customer;
import io.github.zdeneklach.vbap.exception.DbException;
import io.github.zdeneklach.vbap.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/computers")
public class ComputerController {
    @Autowired
    ComputerService computerService;

    @GetMapping("/list")
    private ResponseEntity<List<Computer>> listAllComputers(){
        return ResponseEntity.status(HttpStatus.OK).body(computerService.getAllComputers());
    }

}
