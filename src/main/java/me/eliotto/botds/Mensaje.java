package me.eliotto.botds;

import org.javacord.api.entity.user.User;

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

    public Date getFecha() {
        return fecha;
    }

    private Date fecha;

    public Mensaje(String content, User autor, Date fecha){
        this.content = content;
        this.autor = autor;
        this.fecha = fecha;
    }


}
