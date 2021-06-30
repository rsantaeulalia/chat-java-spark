package com.asapp.backend.challenge.resources;

import com.asapp.backend.challenge.resources.enums.HealthCheckTypeEnum;

public class HealthResource {
    private HealthCheckTypeEnum health;

    public HealthResource(HealthCheckTypeEnum health) {
        this.health = health;
    }

    public HealthCheckTypeEnum getHealth() {
        return health;
    }

    public void setHealth(HealthCheckTypeEnum health) {
        this.health = health;
    }
}
