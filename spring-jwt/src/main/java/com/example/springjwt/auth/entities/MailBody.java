package com.example.springjwt.auth.entities;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
