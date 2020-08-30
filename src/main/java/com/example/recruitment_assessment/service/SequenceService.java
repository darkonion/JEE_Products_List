package com.example.recruitment_assessment.service;

public interface SequenceService {

    boolean up(Long id);
    boolean down(Long id);
    boolean top(Long id);
    boolean bottom(Long id);
}
