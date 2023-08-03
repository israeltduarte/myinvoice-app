package br.com.isertech.myinvoiceback.service;

import br.com.isertech.myinvoiceback.constants.Messages;
import br.com.isertech.myinvoiceback.dto.ClientDTO;
import br.com.isertech.myinvoiceback.entity.Client;
import br.com.isertech.myinvoiceback.error.exception.ClientNotFoundException;
import br.com.isertech.myinvoiceback.repository.ClientRepository;
import br.com.isertech.myinvoiceback.util.ClientTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {this.clientRepository = clientRepository;}

    public List<Client> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        log.info("ClientService - getAllClients() - List<Client> = {}", clients);
        return clients;
    }

    public Client addClient(ClientDTO clientDTO) {
        Client client = ClientTransformer.fromDTO(clientDTO);
        client = clientRepository.save(client);
        log.info("ClientService - client saved");
        log.info("ClientService - addClient() - Client = {}", client);
        return client;
    }

    public Client getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(Messages.CLIENT_NOT_FOUND));
        log.info("ClientService - getClientById() - Client = {}", client);
        return client;
    }

    public Client updateClient(Long clientId, ClientDTO clientDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(Messages.CLIENT_NOT_FOUND));
        client = ClientTransformer.updateEntity(client, clientDTO);
        client = clientRepository.save(client);
        log.info("ClientService - updateClient() - Client = {}", client);
        return client;
    }

    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
        log.info("ClientService - client deleted");
        log.info("ClientService - deleteClient()");
    }

    public void deleteAllClients() {
        clientRepository.deleteAll();
        log.info("ClientService - clients deleted");
        log.info("ClientService - deleteAllClients()");
    }

}
