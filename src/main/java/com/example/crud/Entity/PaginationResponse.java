package com.example.crud.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationResponse {
    int totalPages;
    Object data;
}
