package uz.najot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.najot.entity.AppClient;

import java.util.Optional;

public interface AppClientRepository extends JpaRepository<AppClient, Integer> {
    Optional<AppClient> findByToken(String token);
}
