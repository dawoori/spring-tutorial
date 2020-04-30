package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Value {
    private Long id;
    private String quote;

    public Value() {
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
