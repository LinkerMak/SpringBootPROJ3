package com.example.springbootproj.service.email;

public abstract class AbstactEmailContext {
    private String from;
    private String to;
    private String subject;
    private String email;
    private String attachment;
    private String fromDisplayName;
    private String emailLanguage;
    private String displayName;
    private String templateLocation;
    private Map <String, Object> context;
}
