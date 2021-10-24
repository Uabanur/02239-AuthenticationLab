package shared;

import java.time.LocalDateTime;

public class Token {
    private String user;
    LocalDateTime expirationTime;

    Token(String user, long minutes) {
         this.user = user;
         this.expirationTime = LocalDateTime.now().plusMinutes(minutes);
    }

    boolean isValid() {
        return LocalDateTime.now().isBefore(expirationTime);
    }
}
