package com.ankit.module35.dto;

import com.ankit.module35.entity.type.BloodGroupType;
import lombok.Data;

@Data
public class BloodGroupCount {
    private final BloodGroupType bloodGroupType;
    private  final  Long Count;
}
