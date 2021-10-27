package server;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;

public class SessionManager {
    private static ConcurrentHashMap<UUID, SessionToken> _sessions= new ConcurrentHashMap<UUID, SessionToken>();
    private static int timeoutHours = 1;

    public static synchronized UUID createSession(String username) {
        UUID token = UUID.randomUUID();
        _sessions.put(token, new SessionToken(token, username));
        return token;
    }

    public static synchronized boolean validateSessionToken(UUID token){
        SessionToken sessionToken = _sessions.get(token);
        if (sessionToken == null) return false;
        return isValidToken(sessionToken);
    }

    public static void clearExpired() {
        _sessions.entrySet().removeIf(entry -> !isValidToken(entry.getValue()));
    }

    public static boolean isValidToken(SessionToken token) {
        return token.StartTime.isAfter(LocalDateTime.now().minusHours(timeoutHours));
    }

    public static ConcurrentHashMap<UUID, SessionToken> get_sessions() {
        return _sessions;
    }
}
