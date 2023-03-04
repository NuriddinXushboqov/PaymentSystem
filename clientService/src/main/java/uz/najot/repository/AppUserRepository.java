package uz.najot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.najot.entity.AppUser;


import java.util.Optional;

@Repository
public interface AppUserRepository {
    default Optional<AppUser> findByUsername(String username) {
        return null;
    }
}
