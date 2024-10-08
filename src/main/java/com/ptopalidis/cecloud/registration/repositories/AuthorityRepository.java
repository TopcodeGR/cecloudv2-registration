package com.ptopalidis.cecloud.registration.repositories;

import com.ptopalidis.cecloud.registration.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    public  Authority findAuthorityByName(String name);
}
