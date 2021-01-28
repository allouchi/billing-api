package com.aliateck.fact.infrastructure.repository.user;

import com.aliateck.fact.infrastructure.models.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByMail(String email);

  Optional<UserEntity> findByFirstName(String name);

  Optional<UserEntity> findByMailAndPassword(String mail, String password);
  
  @Query("SELECT u FROM UserEntity u WHERE u.mail = ?1")
  void getUserByMail( @Param("mail") String mail);
  
}
