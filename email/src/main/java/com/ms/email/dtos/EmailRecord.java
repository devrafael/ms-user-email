package com.ms.email.dtos;

public record EmailRecord(Integer userId,
                          String emailTo,
                          String title,
                          String content) {
}
