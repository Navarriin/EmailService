package com.navarro.emailservice.core;

public record EmailRequest(String to, String subject, String body) {
}
