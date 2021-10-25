package server;

import java.time.LocalDateTime;

public class SessionToken {
    public byte[] Content;
    public String User;
    public LocalDateTime StartTime;

    public SessionToken(byte[] content, String user){
        this.Content = content;
        this.User = user;
        this.StartTime = LocalDateTime.now();
    }
}
