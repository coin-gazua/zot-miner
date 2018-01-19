package com.coingazua.zotminer.business.user.repository;

import com.coingazua.zotminer.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
