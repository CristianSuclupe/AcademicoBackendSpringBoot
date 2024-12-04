package com.academico.backendjava.factories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academico.backendjava.strategies.IRoleStrategy;


@Service
public class RoleStrategyFactory {

    private final Map<String, IRoleStrategy> strategies = new HashMap<>();

    public RoleStrategyFactory(List<IRoleStrategy> strategyList) {
        for(IRoleStrategy strategy : strategyList) {
            strategies.put(strategy.getRoleName(), strategy);
        }
    }

    public IRoleStrategy getStrategy(String roleName) {
        return Optional.ofNullable(strategies.get(roleName))
            .orElseThrow(() -> new IllegalArgumentException("No se encontró estrategia para " + roleName));
    }
}
