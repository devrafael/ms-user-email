package com.ms.user.dtos;

public record EmailRecord(Integer userId,
                          String emailTo,
                          String title,
                          String content) {
}
