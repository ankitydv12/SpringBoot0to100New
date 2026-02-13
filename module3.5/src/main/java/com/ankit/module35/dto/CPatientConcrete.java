package com.ankit.module35.dto;

import lombok.Data;

@Data
public class CPatientConcrete {
    /*Its mandtory to make the these field final because @Data provide allargsConstructor */
    private final Long id;
    private final String name;
}
