package se.iths.java24.spring25.controllers.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

record EventDTO (Long id, String name, @JsonFormat(pattern="yyyy-MM-dd") LocalDate date) {
}
