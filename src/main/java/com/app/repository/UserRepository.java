package com.app.repository;

import com.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for User entity.
 * Provides CRUD operations and query methods for User data.
 * 
 * This interface extends JpaRepository, enabling standard data access methods such as
 * save, findById, findAll, deleteById, etc. No custom query methods are defined here,
 * adhering to Spring Data JPA conventions and keeping the interface clean and maintainable.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // Default query methods provided by JpaRepository are sufficient.
}
