package br.com.isertech.myinvoice.myinvoiceback.controller;

import br.com.isertech.myinvoice.myinvoiceback.dto.ClientDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Client;
import br.com.isertech.myinvoice.myinvoiceback.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@Transactional
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Page<Client>> getAllClients(Pageable pageable) {

        Page<Client> clients = clientService.getAllClients(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody ClientDTO clientDTO) {

        Client client = clientService.addClient(clientDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable String clientId) {

        Client client = clientService.getClientById(clientId);

        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable String clientId, @RequestBody ClientDTO clientDTO) {

        Client client = clientService.updateClient(clientId, clientDTO);

        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable String clientId) {

        clientService.deleteClient(clientId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllClients() {

        clientService.deleteAllClients();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
