package se.iths.java24.spring25.controllers.api;

import java.time.LocalDate;

record InternshipDTO (Long id, String title, String company, String location, String description,
                             LocalDate startDate, LocalDate endDate) {
}