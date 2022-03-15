package com.example.CinemaREST.theater.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Theater {

    @JsonProperty("total_rows")
    int rows;
    @JsonProperty("total_columns")
    int cols;
    @JsonProperty("available_seats")
    List<Seat> seats = new ArrayList<>();

    public Theater(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        for (int row = 0; row < cols; ++row) {
            for (int col = 0; col < this.rows; ++col) {
                seats.add(new Seat(row + 1, col + 1));
            }
        }
    }
}
