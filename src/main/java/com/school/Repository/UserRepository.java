package com.school.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.User;
import com.school.enums.UserRole;
import com.school.responsedto.UserResponse;
//interface to interface => extends
//class to class =>extends
@Repository
public interface UserRepository extends JpaRepository<User,Integer>  {

	boolean existsByUserRole(UserRole userRole);

	Optional<User> findByUserName(String userName);

	boolean existsByIsDeletedAndUserRole(boolean b, UserRole userRole);


}
