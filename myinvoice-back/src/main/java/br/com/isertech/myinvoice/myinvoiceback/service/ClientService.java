package br.com.isertech.myinvoice.myinvoiceback.service;

import br.com.isertech.myinvoice.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoice.myinvoiceback.dto.ClientDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Client;
import br.com.isertech.myinvoice.myinvoiceback.entity.MIUser;
import br.com.isertech.myinvoice.myinvoiceback.error.exception.ClientNotFoundException;
import br.com.isertech.myinvoice.myinvoiceback.error.exception.UserNotFoundException;
import br.com.isertech.myinvoice.myinvoiceback.repository.ClientRepository;
import br.com.isertech.myinvoice.myinvoiceback.util.ClientTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientService {

    private final UserService userService;
    private final ClientRepository clientRepository;

    public ClientService(UserService userService, ClientRepository clientRepository) {
        this.userService = userService;
        this.clientRepository = clientRepository;
    }

    public Page<Client> getAllClients(Pageable pageable) {

        Page<Client> clients = clientRepository.findAll(pageable);
        log.info("ClientService - getAllClients() - Page<Client>={}", clients);

        return clients;
    }

    public Client addClient(ClientDTO clientDTO) {

        MIUser user = userService.getUserById(clientDTO.getUserId());
        Client client = ClientTransformer.fromDTO(clientDTO);
        client.setUser(user);

        client = clientRepository.save(client);
        log.info("ClientService - addClient() - Client={}", client);

        return client;
    }

    public Client getClientById(String clientId) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(Messages.CLIENT_NOT_FOUND));
        log.info("ClientService - getClientById() - Client={}", client);

        return client;
    }

    public Client updateClient(String clientId, ClientDTO clientDTO) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(Messages.CLIENT_NOT_FOUND));
        client = ClientTransformer.updateEntity(client, clientDTO);
        client = clientRepository.save(client);
        log.info("ClientService - updateClient() - Client={}", client);

        return client;
    }

    public void deleteClient(String clientId) {

        clientRepository.deleteById(clientId);
        log.info("ClientService - deleteClient()");
    }

    public void deleteAllClients() {

        clientRepository.deleteAll();
        log.info("ClientService - deleteAllClients()");
    }

    public Page<Client> getAllClientsByUserId(String id, Pageable pageable) {

        MIUser user = userService.getUserById(id);
        Page<Client> clients = clientRepository.findAllByUserId(user.getId(), pageable);
        log.info("ClientService - getAllClientsByUserId() - Page<Client>={}", clients);

        return clients;
    }
}
