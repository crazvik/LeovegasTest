package se.jljung.LeovegasTest.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;
    private String activeSession;
    private Long playerId;

    public Session() {
    }

    public Session(String activeSession, Long playerId) {
        this.activeSession = activeSession;
        this.playerId = playerId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getActiveSession() {
        return activeSession;
    }

    public void setActiveSession(String activeSession) {
        this.activeSession = activeSession;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(sessionId, session.sessionId) && Objects.equals(activeSession, session.activeSession) && Objects.equals(playerId, session.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, activeSession, playerId);
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", activeSession='" + activeSession + '\'' +
                ", playerId=" + playerId +
                '}';
    }
}
