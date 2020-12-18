package ru.sbt.course.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "currency")
public class Currency {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDate date;
    private double value;

    public Currency() {
    }

    public Currency(LocalDate date, double value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }
}
