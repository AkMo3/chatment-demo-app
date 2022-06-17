package com.akmo.chatmentdemoapp.controllers;

import com.akmo.chatmentdemoapp.entity.ResponseLog;
import com.akmo.chatmentdemoapp.repository.ResponseLogRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
@AllArgsConstructor
public class AdminController {

    private final ResponseLogRepository responseLogRepository;

    @GetMapping("log")
    public ResponseEntity<List<ResponseLogWithoutId>> getAllResponseLogs() {
        List<ResponseLog> responseLogList = responseLogRepository.getAllByOrderByDateTimeDesc();
        ArrayList<ResponseLogWithoutId> logWithoutIds = new ArrayList<>();
        responseLogList.forEach(e -> {
            ResponseLogWithoutId logWithoutId = new ResponseLogWithoutId(e.getIP(), e.getMessage(), e.getDateTime());
            logWithoutIds.add(logWithoutId);
        });
        return ResponseEntity.ok().body(logWithoutIds);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ResponseLogWithoutId {
        public String ip;
        public String message;
        public LocalDateTime time;
    }
}
