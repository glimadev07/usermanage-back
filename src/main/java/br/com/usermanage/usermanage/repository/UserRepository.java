package br.com.usermanage.usermanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usermanage.usermanage.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    
}
