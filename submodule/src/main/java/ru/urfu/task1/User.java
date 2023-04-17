package ru.urfu.task1;

import java.util.Arrays;
import java.util.Objects;

/**
 * Пользователь
 */
public class User
{
    /**
     *  Имя пользователя
     */
    private String username;

    /**
     * Электронная почта пользователя
     */
    private String email;

    /**
     * Хэш пароля пользователя
     */
    private byte[] passwordHash;

    public User(){}

    public User(String username, String email, byte[] passwordHash)
    {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getUsername()
    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public byte[] getPasswordHash()
    {
        return passwordHash;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(email, user.email)) return false;
        return Arrays.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode()
    {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(passwordHash);
        return result;
    }
}
