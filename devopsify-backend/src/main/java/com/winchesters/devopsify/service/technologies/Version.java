package com.winchesters.devopsify.service.technologies;

import lombok.Data;

@Data
public class Version {
    private int major;
    private int minor;
    private int patch;
}
