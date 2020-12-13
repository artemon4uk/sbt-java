package ru.sbt.course;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuditService {
    private final Map<String, String> logs = new HashMap<>();

    public void addLog(User user, String log) {
        logs.put(user.getName(), log);
    }

    public Map<String, String> audit() {
        return logs;
    }
}
