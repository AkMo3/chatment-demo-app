package com.akmo.chatmentdemoapp.repository;

import com.akmo.chatmentdemoapp.entity.ResponseLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseLogRepository extends JpaRepository<ResponseLog, Long> {
    public List<ResponseLog> getAllByOrderByDateTimeDesc();
}
