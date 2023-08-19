package br.com.isertech.myinvoiceback.repository;

import br.com.isertech.myinvoiceback.entity.MIUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MIUser, Long>, JpaSpecificationExecutor<MIUser> {

    Optional<MIUser> findByUsername(String username);

    Optional<MIUser> findByEmail(String email);

}
