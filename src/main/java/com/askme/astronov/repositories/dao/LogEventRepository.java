package com.askme.astronov.repositories.dao;

import com.askme.astronov.repositories.entity.LogEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogEventRepository extends JpaRepository<LogEvent, Long> {

}
