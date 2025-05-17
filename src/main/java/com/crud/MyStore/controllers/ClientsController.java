package com.crud.MyStore.controllers;

import com.crud.MyStore.models.Client;
import com.crud.MyStore.models.ClientDto;
import com.crud.MyStore.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientRepository repo;

    @GetMapping
    public String getClients(Model model) {
        List<Client> clients = repo.getClient();
        model.addAttribute("clients", clients);
        return "clients/index";
    }


    @GetMapping("/create")
    public String showCreatePage(Model model) {
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);
        return "clients/create";
    }

    @PostMapping("/create")
    public String createClient(
            @Valid @ModelAttribute ClientDto clientDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "clients/create";
        }

        Client client = new Client();
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));


        repo.createClient(client);

        return "redirect:/clients";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {
        Client client = repo.getClient(id);
        if (client == null) {
            return "redirect:/clients";
        }

        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setFirstname(client.getFirstname());
        clientDto.setLastname(client.getLastname());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setAddress(client.getAddress());

        model.addAttribute("clientDto", clientDto);
        return "clients/edit";
    }

    @PostMapping("/edit")
    public String updateClient(
            Model model,
            @Valid @ModelAttribute ClientDto clientDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "clients/edit";
        }

        Client client = repo.getClient(clientDto.getId());
        if (client == null) {
            return "redirect:/clients";
        }

        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());

        repo.updateClient(client);

        return "redirect:/clients";
    }

    @GetMapping("/delete")
    public String deleteClient(
            @RequestParam int id
){
        repo.deleteClient(id);
        return "redirect:/clients";
}


}

