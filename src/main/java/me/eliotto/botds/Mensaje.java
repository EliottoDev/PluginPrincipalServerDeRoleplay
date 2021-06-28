package me.eliotto.botds;

import org.javacord.api.entity.user.User;

import java.time.LocalDateTime;
import java.util.Date;

public class Mensaje {

    private String content;

    public User getAutor() {
        return autor;
    }

    private User autor;

    public String getContent() {
        return content;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    private LocalDateTime fecha;

    public Mensaje(String content, User autor, LocalDateTime fecha){
        this.content = content;
        this.autor = autor;
        this.fecha = fecha;
    }


}
