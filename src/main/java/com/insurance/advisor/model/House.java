package com.insurance.advisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class House implements AdditionalParam {
    private int id;
    private OwnershipStatus ownership_status;
}
