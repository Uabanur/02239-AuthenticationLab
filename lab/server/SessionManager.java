package server;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.security.SecureRandom;
import java.time.LocalDateTime;

public class SessionManager {
    private static ConcurrentHashMap<String, SessionToken> _sessions= new ConcurrentHashMap<String, SessionToken>();
    private static Random RANDOM = new SecureRandom();
    private static int timeoutHours = 1;

    private static String tokenToKey(byte[] token){
        return Base64.getEncoder().encodeToString(token);
    }
    public static synchronized byte[] createSession(String username) {
        byte[] token = new byte[512];
        RANDOM.nextBytes(token);
        _sessions.put(tokenToKey(token), new SessionToken(token, username));
        return token;
    }

    public static synchronized boolean validateSessionToken(byte[] token){
        SessionToken sessionToken = _sessions.get(tokenToKey(token));
        if (sessionToken == null) return false;
        return sessionToken.StartTime.isAfter(LocalDateTime.now().minusHours(timeoutHours));
    }
}
