package com.chen.star.mapper;

import com.chen.star.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper extends CrudRepository<Account, Long> {

    Account findByAccountname(@Param("accountname") String accountname);

}
