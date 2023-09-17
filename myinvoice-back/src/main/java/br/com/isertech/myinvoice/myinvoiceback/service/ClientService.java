package br.com.isertech.myinvoice.myinvoiceback.service;

import br.com.isertech.myinvoice.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoice.myinvoiceback.dto.ClientDTO;
import br.com.isertech.myinvoice.myinvoiceback.entity.Client;
import br.com.isertech.myinvoice.myinvoiceback.entity.MIUser;
import br.com.isertech.myinvoice.myinvoiceback.error.exception.ClientNotFoundException;
import br.com.isertech.myinvoice.myinvoiceback.repository.ClientRepository;
import br.com.isertech.myinvoice.myinvoiceback.util.ClientTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientService {

    private final UserService userService;
    private final ClientRepository clientRepository;

    public ClientService(UserService userService, ClientRepository clientRepository) {
        this.userService = userService;
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {

        List<Client> clients = clientRepository.findAll();
        log.info("ClientService - getAllClients() - List<Client>={}", clients);

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

    public List<Client> getAllClientsByUserId(String id) {

        MIUser user = userService.getUserById(id);
        List<Client> clients = clientRepository.findAllByUserId(user.getId());
        log.info("ClientService - getAllClientsByUserId() - List<Client>={}", clients);

        return clients;
    }
}
